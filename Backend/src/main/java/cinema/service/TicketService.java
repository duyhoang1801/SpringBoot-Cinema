package cinema.service;

import cinema.dto.TicketCreateForm;
import cinema.dto.TicketDTO;
import cinema.entity.Account;
import cinema.entity.Promotion;
import cinema.entity.Ticket;
import cinema.exception.AppException;
import cinema.exception.ErrorResponseEnum;
import cinema.repository.AccountRepository;
import cinema.repository.IPromotionRepository;
import cinema.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicket{
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private IPromotionRepository promotionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public TicketDTO create(TicketCreateForm form) {
        Optional<Account> accountOptional = accountRepository.findById(form.getAccountId());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.ACCOUNT_NOT_FOUND );
        }
        Optional<Promotion> promotionOptional = promotionRepository.findById(form.getPromotionId());
        if (promotionOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.PROMOTION_NOT_FOUND );
        }
        Account account = accountOptional.get();
        Promotion promotion = promotionOptional.get();
        Ticket ticket = new Ticket();
        ticket.setPrice(form.getPrice());
        ticket.setAccount(account);
        ticket.setPromotion(promotion);
        Ticket savedTicket = ticketRepository.save(ticket);
        return modelMapper.map(savedTicket, TicketDTO.class);
    }


    @Override
    public void delete(Integer id) {
        ticketRepository.deleteById(id);
    }
}

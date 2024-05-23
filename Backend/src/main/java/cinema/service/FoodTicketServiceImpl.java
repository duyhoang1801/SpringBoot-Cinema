package cinema.service;

import cinema.dto.FoodTicketDto;
import cinema.dto.ReserveSeatDto;
import cinema.entity.FoodTicket;
import cinema.entity.ReserveSeat;
import cinema.form.FoodTicketCreateForm;
import cinema.form.ReserveSeatCreateForm;
import cinema.repository.FoodTicketRepository;
import cinema.repository.ReserveSeatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodTicketServiceImpl implements FoodTicketService {

    private FoodTicketRepository foodTicketRepository;

    private ModelMapper modelMapper;
    @Autowired
    public FoodTicketServiceImpl(
            FoodTicketRepository foodTicketRepository,
            ModelMapper modelMapper
    ) {
        this.foodTicketRepository = foodTicketRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public FoodTicketDto createFoodTicket(FoodTicketCreateForm form) {

        FoodTicket reserveFoodTicket = modelMapper.map(form, FoodTicket.class);
        FoodTicket savedFoodTicket = foodTicketRepository.save(reserveFoodTicket);
        return modelMapper.map(savedFoodTicket, FoodTicketDto.class);

    }

    @Override
    public List<FoodTicketDto> findByTicketId(Integer ticketId) {
        return foodTicketRepository.findByTicketId(ticketId).stream().map((element) -> modelMapper.map(element, FoodTicketDto.class)).collect(Collectors.toList());
    }


}

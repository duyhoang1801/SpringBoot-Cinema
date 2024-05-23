package cinema.service;

import cinema.dto.AccountCreateRequest;
import cinema.dto.AccountSearchRequest;
import cinema.dto.AccountUpdateRequest;
import cinema.entity.Account;
import cinema.entity.FoodTicket;
import cinema.entity.Role;
import cinema.entity.Ticket;
import cinema.exception.AppException;
import cinema.exception.ErrorResponseEnum;
import cinema.repository.*;
import cinema.specification.AccountSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService implements IAccountService, UserDetailsService{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FoodTicketRepository foodTicketRepository;

    @Autowired
    private ReserveSeatRepository reserveSeatRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account create(AccountCreateRequest request) {
        Optional<Account> optionalAccount = accountRepository.findByPhoneNumber(request.getPhoneNumber());
        if(optionalAccount.isPresent()) {
            throw new AppException(ErrorResponseEnum.PHONENUMBER_EXISTED);
        }
        Account account = new Account();
        BeanUtils.copyProperties(request, account);

        String passEncoder = passwordEncoder.encode(request.getPassword());
        account.setPassword(passEncoder);

        account.setRole(Role.USER);

        account = accountRepository.save(account);
        return account;
    }

    @Override
    public Account getById(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new AppException(ErrorResponseEnum.ACCOUNT_NOT_FOUND);
        }
    }

    @Override
    public Account update(AccountUpdateRequest request) {
        Account account = getById(request.getAccountId());
        if (account != null) {
            if(request.getFullName()!=null) {account.setFullName(request.getFullName());}
            if(request.getPassword()!=null) {account.setPassword(request.getPassword());}
            if(request.getDateOfBirth()!=null) {account.setDateOfBirth(request.getDateOfBirth());}
            if(request.getAvatar()!=null) {account.setAvatar(request.getAvatar());}
            if(request.getEmail()!=null) {account.setEmail(request.getEmail());}
            if(request.getPhoneNumber()!=null) {account.setPhoneNumber(request.getPhoneNumber());}
            return accountRepository.save(account);
        } else {
            throw new AppException(ErrorResponseEnum.ACCOUNT_NOT_FOUND);
        }
    }

    @Override
    public void delete(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.ACCOUNT_NOT_FOUND);
        } else {
            accountRepository.deleteById(id);
        }
    }

    @Override
    public Page<Account> search(AccountSearchRequest request) {
        PageRequest pageRequest = null;
        if ("DESC".equals(request.getSortType())) {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).ascending());
        }
        Specification<Account> condition = AccountSpecification.buildCondition(request);
        return accountRepository.findAll(condition, pageRequest);
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException("Số điện thoại không tồn tại");
        }
        Account account = accountOptional.get();
        // Nếu có tồn tại -> tạo ra đối tượng Userdetails từ Account
        /**
         * phonenumber
         * account.getPassword(): password đã được mã hóa
         * authorities: List quyền của user
         */
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(account.getRole());
        return new User(phoneNumber, account.getPassword(), authorities);
    }

    @Override
    public List<Account> findAllByPhoneNumber(AccountSearchRequest request) {
        Specification<Account> condition = AccountSpecification.buildCondition(request);
        List<Account> accounts = accountRepository.findAll(condition);
        return accounts;
    }
}

package cinema.service;

import cinema.dto.AccountCreateRequest;
import cinema.dto.AccountSearchRequest;
import cinema.dto.AccountUpdateRequest;
import cinema.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();

    Account create(AccountCreateRequest request);

    Account getById(int id);

    Account update(AccountUpdateRequest request);

    void delete(int id);

    Page<Account> search(AccountSearchRequest request);

    List<Account> findAllByPhoneNumber(AccountSearchRequest request);
}

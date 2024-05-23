package cinema.controller;

import cinema.dto.AccountCreateRequest;
import cinema.dto.AccountDTO;
import cinema.dto.AccountSearchRequest;
import cinema.dto.AccountUpdateRequest;
import cinema.entity.Account;
import cinema.repository.AccountRepository;
import cinema.service.AccountService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "**")
@RestController
@Validated
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get-all")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public List<AccountDTO> getAll() {
        List<Account> entities = accountService.getAll();
        List<AccountDTO> dtos = modelMapper.map(entities, new TypeToken<List<AccountDTO>>() {}.getType());
        return dtos;
    }

    @PostMapping("/find-all-by-phone-number")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public List<AccountDTO> findAllByPhoneNumber(@RequestBody AccountSearchRequest request) {
        List<Account> entities = accountService.findAllByPhoneNumber(request);
        List<AccountDTO> dtos = modelMapper.map(entities, new TypeToken<List<AccountDTO>>() {}.getType());
        return dtos;
    }

    @PostMapping("/create")
    public AccountDTO create(@RequestBody @Valid AccountCreateRequest request){
        Account entity = accountService.create(request);
        AccountDTO dto = modelMapper.map(entity, new TypeToken<AccountDTO>() {}.getType());
        return  dto;
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER')")
    public AccountDTO getById(@PathVariable int id) {
        Account entity = accountService.getById(id);
        AccountDTO dto = modelMapper.map(entity, new TypeToken<AccountDTO>() {}.getType());
        return  dto;
    }

    @PutMapping("/update")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER')")
    public AccountDTO update(@RequestBody @Valid AccountUpdateRequest request){
        Account entity = accountService.update(request);
        AccountDTO dto = modelMapper.map(entity, new TypeToken<AccountDTO>() {}.getType());
        return  dto;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String delete(@PathVariable int id) {
        accountService.delete(id);
        return "Đã xóa thành công";
    }

    @PostMapping("/search")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public Page<AccountDTO> search(@RequestBody AccountSearchRequest request) {
        Page<Account> entites = accountService.search(request);
        Page<AccountDTO> dtos = entites.map(p -> modelMapper.map(p,AccountDTO.class));
        return dtos;
    }
}

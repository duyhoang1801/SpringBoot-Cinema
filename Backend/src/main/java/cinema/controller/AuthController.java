package cinema.controller;

import cinema.dto.LoginDto;
import cinema.dto.LoginRequest;
import cinema.dto.UpdatePassRequest;
import cinema.entity.Account;
import cinema.entity.Role;
import cinema.exception.AppException;
import cinema.exception.ErrorResponseEnum;
import cinema.repository.AccountRepository;
import cinema.utils.JWTTokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @PostMapping("/update-pass")
    public String encodePass(@RequestBody UpdatePassRequest request){
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(request.getPhoneNumber());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.PHONENUMBER_NOT_EXISTED);
        }
        Account account = accountOptional.get();
        boolean checkPassword = passwordEncoder.matches(request.getPasswordCurrent(), account.getPassword());
        if (!checkPassword) {
            return "Mật khẩu hiện tại không đúng";
        }
        String passEncoder = passwordEncoder.encode(request.getPasswordNew());
        account.setPassword(passEncoder);
        account = accountRepository.save(account);
        return "Cập nhật thành công";
    }

    @PostMapping("/check-pass")
    public boolean checkPass(@RequestBody LoginRequest request) {
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(request.getPhoneNumber());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.PHONENUMBER_NOT_EXISTED);
        }
        Account account = accountOptional.get();
        boolean checkPassword = passwordEncoder.matches(request.getPassword(), account.getPassword());
        return checkPassword;
    }

    /**
     * Hàm login JWT: Cách này cần permitAll() với api này để xử lý dữ liệu
     * @param request: Đối tượng gồm phonenumber và password
     * @return
     */
    @PostMapping("/login-jwt")
    public LoginDto loginJWT(@RequestBody LoginRequest request) {
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(request.getPhoneNumber());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.PHONENUMBER_NOT_EXISTED);
        }
        Account account = accountOptional.get();
        boolean checkPassword = passwordEncoder.matches(request.getPassword(), account.getPassword());
        if (!checkPassword) {
            throw new AppException(ErrorResponseEnum.WRONG_PASSWORD);
        }
        // Login
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account, loginDto);
        loginDto.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        String token = jwtTokenUtils.createAccessToken(loginDto);
        loginDto.setToken(token);
        return loginDto;
    }

}

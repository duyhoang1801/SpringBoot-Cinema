package cinema.dto;

import cinema.entity.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AccountCreateRequest extends BaseRequest{
    @NotBlank(message = "Mật khẩu không được để trống")
    @Length(max = 50, message = "Mật khẩu không được quá 50 ký tự")
    private String password;

    @Length(max = 50, message = "Tên không được quá 50 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Length(max = 50, message = "Email không được quá 50 ký tự")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Length(max = 50, message = "Số điện thoại không được quá 50 ký tự")
    private String phoneNumber;

    private Gender gender;

    private String avatar;

    private Date dateOfBirth;
}

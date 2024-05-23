package cinema.dto;

import cinema.entity.Gender;
import cinema.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class AccountUpdateRequest {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải lớn hơn 0")
    private int accountId;

    private String password;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String avatar;

    private LocalDate dateOfBirth;

    private Gender gender;

}

package cinema.dto;

import cinema.entity.Role;
import lombok.Data;

@Data
public class LoginDto {
    private int accountId;
    private String phoneNumber;
    private Role role;
    private String fullName;
    private String avatar;

    private String userAgent; // Tên trình duyệt đang sử dụng
    private String token;
}

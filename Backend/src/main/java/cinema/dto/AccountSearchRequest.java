package cinema.dto;

import lombok.Data;

@Data
public class AccountSearchRequest extends BaseRequest{
    private String phoneNumber;
    private String fullName;
    private String email;
}

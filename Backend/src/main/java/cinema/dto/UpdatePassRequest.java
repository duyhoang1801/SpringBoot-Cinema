package cinema.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePassRequest {
    private String phoneNumber;

    private String passwordCurrent;

    private String passwordNew;
}

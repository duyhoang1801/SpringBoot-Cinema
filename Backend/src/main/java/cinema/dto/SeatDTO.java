package cinema.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeatDTO {
    private Integer id;

    private Character row;

    private Integer column;

    private Boolean status;
}

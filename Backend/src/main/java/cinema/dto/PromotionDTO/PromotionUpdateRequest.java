package cinema.dto.PromotionDTO;

import cinema.entity.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PromotionUpdateRequest {
    private int id;
    private String code;
    private float discount;
//    private Boolean status;
    private Date expiration_date;
}

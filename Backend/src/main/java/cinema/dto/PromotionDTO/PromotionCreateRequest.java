package cinema.dto.PromotionDTO;

import cinema.entity.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PromotionCreateRequest {

    private String code;

    private float discount;

    private LocalDate expiration_date;

    private String image;

    private String content;

//    private Boolean status;
}

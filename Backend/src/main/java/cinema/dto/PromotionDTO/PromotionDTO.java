package cinema.dto.PromotionDTO;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
public class PromotionDTO {
    private int id;

    private String code;

    private float discount;

    private String image;

    private String content;

    private LocalDate expiration_date;
}

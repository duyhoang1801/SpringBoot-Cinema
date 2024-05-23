package cinema.dto;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class TicketCreateForm {
    private BigDecimal price;
    private Integer accountId;
    private Integer promotionId;
}

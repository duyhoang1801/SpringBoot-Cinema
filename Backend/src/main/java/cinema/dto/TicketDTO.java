package cinema.dto;

import cinema.entity.FoodTicket;
import cinema.entity.ReserveSeat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class TicketDTO {
    private Integer id;
    private BigDecimal price;
    private Integer accountId;
    private Integer promotionId;
    private List<FoodTicketDto> foodTickets;
    private List<ReserveSeatDto> reserveSeat;
}

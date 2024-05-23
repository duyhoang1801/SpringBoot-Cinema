package cinema.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReserveSeatCreateForm {

    private Integer pkMovie_showtimeId;

    private Integer pkTicketId;

    private Integer pkSeatId;

    private Integer movieShowtimeId;

    private Integer ticketId;

    private Integer seatId;
}

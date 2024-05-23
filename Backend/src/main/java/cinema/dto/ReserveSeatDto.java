package cinema.dto;

import cinema.entity.Movie_showtime;
import cinema.entity.ReserveSeatPrimaryKey;
import cinema.entity.Seat;
import cinema.entity.Ticket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class ReserveSeatDto {

    private Integer movieShowtimeId;

    private Integer ticketId;

    private Integer seatId;
}

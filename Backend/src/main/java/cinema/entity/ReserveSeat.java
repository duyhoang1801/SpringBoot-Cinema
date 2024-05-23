package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ghe_da_dat")
public class ReserveSeat {

    @EmbeddedId
    private ReserveSeatPrimaryKey pk;

    @ManyToOne
    @MapsId(value = "ticketId")
    @JoinColumn(name = "id_ve", nullable = false)
    private Ticket ticket;


    @ManyToOne
    @MapsId(value = "movie_showtimeId")
    @JoinColumn(name = "id_suat_chieu", nullable = false)
    private Movie_showtime movieShowtime;


    @ManyToOne
    @MapsId(value = "seatId")
    @JoinColumn(name = "id_ghe", nullable = false)
    private Seat seat;







}

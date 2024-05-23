package cinema.entity;

import io.swagger.models.auth.In;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ReserveSeatPrimaryKey {

    @Column(name = "id_suat_chieu", nullable = false)
    private Integer movie_showtimeId;

    @Column(name = "id_ve", nullable = false)
    private Integer ticketId;

    @Column(name = "id_ghe", nullable = false)
    private Integer seatId;
}

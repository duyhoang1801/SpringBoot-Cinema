package cinema.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "suat_chieu")
public class Movie_showtime {

    @Id
    @Column(name = "id_suat_chieu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ngay_chieu")
    private LocalDate dateShow;

    @Column(name = "gio_chieu")
    private Time timeShow;

    @ManyToOne
    @JoinColumn(name = "id_phong_chieu")
    private CinemaRoom cinemaRoom;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_film")
    private Film film;

    @OneToMany(mappedBy = "movieShowtime", orphanRemoval = true)
    private List<ReserveSeat> reserveSeat;
}

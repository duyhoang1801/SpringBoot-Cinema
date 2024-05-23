package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "phong_chieu")
public class CinemaRoom {
    @Id
    @Column(name = "id_phong_chieu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cinemaRoomId;

    @Column(name = "so_phong")
    private int numberOfRoom;

    @Column(name = "trang_thai")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "id_rap")
    private Theater theater;

    @OneToMany(mappedBy = "cinemaRoom", orphanRemoval = true)
    private List<Seat> seat;

    @OneToMany(mappedBy = "cinemaRoom", orphanRemoval = true)
    private List<Movie_showtime> movieShowtime;
}

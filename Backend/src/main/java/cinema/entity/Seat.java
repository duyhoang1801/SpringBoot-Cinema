package cinema.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "ghe")
public class Seat {

    @Id
    @Column(name = "id_ghe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hang")
    private Character row;

    @Column(name = "cot")
    private Integer column;

    @Column(name = "trang_thai")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_phong_chieu")
    private CinemaRoom cinemaRoom;
}

package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "rap")
public class Theater {


    @Id
    @Column(name = "id_rap")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_rap")
    private String name;

    @OneToMany(mappedBy = "theater", orphanRemoval = true)
    private List<CinemaRoom> cinemaRoom;
}

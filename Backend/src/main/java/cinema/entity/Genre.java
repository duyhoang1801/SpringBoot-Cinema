package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "the_loai")
public class Genre {

    @Id
    @Column(name = "id_the_loai")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "ten_the_loai")
    private String name;


    @OneToMany(mappedBy = "genre")
    private List<Film> films;
}

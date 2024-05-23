package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "quoc_gia")
public class Country {

    @Id
    @Column(name = "id_quoc_gia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_quoc_gia")
    private String name;


    @OneToMany(mappedBy = "country")
    private List<Film> films;



}

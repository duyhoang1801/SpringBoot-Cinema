package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "the_loai_do_an")
public class FoodType {
    @Id
    @Column(name = "id_the_loai_do_an")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodTypeId;

    @Column(name = "ten_the_loai")
    private String categoryFood;

//    @OneToMany(mappedBy = "foodType")
//    private List<Food> foods;
}

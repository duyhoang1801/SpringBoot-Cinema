package cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "do_an")
public class Food {
    @Id
    @Column(name = "id_do_an")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;

    @Column(name = "ten_do_an")
    private String nameFood;

    @Column(name = "gia")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_the_loai_do_an")
    private FoodType foodType;


    @OneToMany(mappedBy = "food")
    private List<FoodTicket> foodTickets;
}

package cinema.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class FoodTicketPrimaryKey {
    @Column(name = "id_do_an", nullable = false)
    private int foodFoodId;

    @Column(name = "id_ve", nullable = false)
    private int ticketId;
}

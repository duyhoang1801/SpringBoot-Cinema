package cinema.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ve_do_an")
public class FoodTicket {
    @EmbeddedId
    private FoodTicketPrimaryKey pk;

    @ManyToOne
    @MapsId(value = "foodFoodId")
    @JoinColumn(name = "id_do_an", nullable = false)
    private Food food;

    @ManyToOne
    @MapsId(value = "ticketId")
    @JoinColumn(name = "id_ve", nullable = false)
    private Ticket ticket;

}

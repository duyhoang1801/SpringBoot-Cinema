package cinema.repository;

import cinema.entity.Food;
import cinema.entity.FoodTicket;
import cinema.entity.FoodTicketPrimaryKey;
import cinema.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodTicketRepository extends JpaRepository<FoodTicket, FoodTicketPrimaryKey> {
    List<FoodTicket> findByTicketId(Integer ticketId);
}

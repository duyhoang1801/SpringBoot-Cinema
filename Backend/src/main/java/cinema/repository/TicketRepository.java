package cinema.repository;

import cinema.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByAccountAccountId(int id);
    void deleteAllByAccountAccountId(int id);
}

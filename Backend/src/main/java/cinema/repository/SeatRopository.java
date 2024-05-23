package cinema.repository;

import cinema.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SeatRopository extends JpaRepository<Seat, Integer>, JpaSpecificationExecutor<Seat> {

}

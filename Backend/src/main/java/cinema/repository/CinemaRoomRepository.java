package cinema.repository;

import cinema.entity.CinemaRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Integer> {
}

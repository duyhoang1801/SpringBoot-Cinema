package cinema.repository;

import cinema.entity.Movie_showtime;
import cinema.entity.ReserveSeat;
import cinema.entity.ReserveSeatPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReserveSeatRepository extends JpaRepository<ReserveSeat, ReserveSeatPrimaryKey>{


    List<ReserveSeat> findAllByMovieShowtimeId(int movieShowtimeId);

    void deleteAllByTicketId(int TicketId);
}

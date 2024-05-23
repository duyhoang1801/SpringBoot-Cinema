package cinema.repository;

import cinema.entity.Movie_showtime;
import cinema.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MovieshowtimeRepository extends JpaRepository<Movie_showtime, Integer>, JpaSpecificationExecutor<Movie_showtime> {

    List<Movie_showtime> findAllByFilm_Id(int filmId);
    void deleteAllByFilm_Id(int filmId);

}

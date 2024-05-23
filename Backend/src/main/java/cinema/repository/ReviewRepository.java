package cinema.repository;

import cinema.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>, JpaSpecificationExecutor<Review> {
    List<Review> findAllByFilmId(int filmId);

//    @Query(value = "select avg(r.rating) from Review r where r.film = :film")
//    float abc(Film film);

    List<Review> findAllByAccountAccountId(int accountId);
    void deleteAllByAccountAccountId(int accountId);
    void deleteAllByFilmId(int filmId);
}

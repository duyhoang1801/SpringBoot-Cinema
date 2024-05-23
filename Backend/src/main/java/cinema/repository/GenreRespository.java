package cinema.repository;

import cinema.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRespository extends JpaRepository<Genre, Integer> {
    Genre findByName(String genreName);
}

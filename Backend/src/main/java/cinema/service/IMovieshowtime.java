package cinema.service;

import cinema.dto.movieShowTimeDTO.Movie_showtimeCreateRequest;
import cinema.dto.movieShowTimeDTO.Movie_showtimeUpdateRequest;
import cinema.dto.reviewDTO.ReviewCreateRequest;
import cinema.dto.reviewDTO.ReviewSearchRequest;
import cinema.dto.reviewDTO.ReviewUpdateRequest;
import cinema.entity.Movie_showtime;
import cinema.entity.Review;
import cinema.form.MovieShowTimeFilterForm;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMovieshowtime {
    List<Movie_showtime> getAll();

    void create(Movie_showtimeCreateRequest request);

    Movie_showtime getById(int id);

    void update(Movie_showtimeUpdateRequest request);

    void delete(int id);

    Page<Movie_showtime> search(MovieShowTimeFilterForm request);

    List<Movie_showtime> findByFilm(int filmId);

}

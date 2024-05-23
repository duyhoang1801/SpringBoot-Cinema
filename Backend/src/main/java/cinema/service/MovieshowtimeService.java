package cinema.service;

import cinema.dto.movieShowTimeDTO.Movie_showtimeCreateRequest;
import cinema.dto.movieShowTimeDTO.Movie_showtimeUpdateRequest;
import cinema.dto.reviewDTO.ReviewCreateRequest;
import cinema.dto.reviewDTO.ReviewSearchRequest;
import cinema.dto.reviewDTO.ReviewUpdateRequest;
import cinema.entity.*;
import cinema.exception.AppException;
import cinema.exception.ErrorResponseEnum;
import cinema.form.MovieShowTimeFilterForm;
import cinema.repository.CinemaRoomRepository;
import cinema.repository.FilmRepository;
import cinema.repository.MovieshowtimeRepository;
import cinema.repository.ReviewRepository;
import cinema.specification.MovieShowTimeSpecification;
import cinema.specification.ReviewSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieshowtimeService implements IMovieshowtime{
    @Autowired
    private MovieshowtimeRepository movieshowtimeRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Override
    public List<Movie_showtime> getAll() {
        return movieshowtimeRepository.findAll();
    }

    @Override
    public void create(Movie_showtimeCreateRequest request) {
        Optional<Film> filmOptional = filmRepository.findById(request.getFilmId());
        Optional<CinemaRoom> cinemaRoomOptional = cinemaRoomRepository.findById(request.getCinemaRoomID());

        if (cinemaRoomOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.CINEMA_ROOM_NOT_FOUND );
        }
        if (filmOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.FILM_NOT_FOUND);
        }
        Film film = filmOptional.get();
        CinemaRoom cinemaRoom = cinemaRoomOptional.get();

        Movie_showtime movie_showtime = new Movie_showtime();

        movie_showtime.setFilm(film);
        movie_showtime.setCinemaRoom(cinemaRoom);
        movie_showtime.setDateShow(request.getDateShow());
        movie_showtime.setTimeShow(request.getTimeShow());
        movieshowtimeRepository.save(movie_showtime);
    }

    @Override
    public Movie_showtime getById(int id) {
        Optional<Movie_showtime> movie_showtimeOptional = movieshowtimeRepository.findById(id);
        if (movie_showtimeOptional.isPresent()) {
            return movie_showtimeOptional.get();
        } else {
            throw new AppException(ErrorResponseEnum.MOVIESHOWTIME_NOT_FOUND);
        }
    }

    @Override
    public void update(Movie_showtimeUpdateRequest request) {
        Movie_showtime movie_showtime = getById(request.getId());
        if (movie_showtime != null) {
            BeanUtils.copyProperties(request, movie_showtime);
             movieshowtimeRepository.save(movie_showtime);

        } else {
            throw new AppException(ErrorResponseEnum.MOVIESHOWTIME_NOT_FOUND);
        }
    }

    @Override
    public void delete(int id) {
        Optional<Movie_showtime> reviewOptional = movieshowtimeRepository.findById(id);
        if (reviewOptional.isPresent()) {
            movieshowtimeRepository.deleteById(id);
        } else {
            throw new AppException(ErrorResponseEnum.MOVIESHOWTIME_NOT_FOUND);
        }
    }

    @Override
    public Page<Movie_showtime> search(MovieShowTimeFilterForm request) {
        PageRequest pageRequest = null;
        if ("DESC".equals(request.getSortType())) {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        }
        Specification<Movie_showtime> condition = MovieShowTimeSpecification.buildCondition(request);
        return movieshowtimeRepository.findAll(condition, pageRequest);
    }

    @Override
    public List<Movie_showtime> findByFilm(int filmId) {
        return movieshowtimeRepository.findAllByFilm_Id(filmId);
    }

}

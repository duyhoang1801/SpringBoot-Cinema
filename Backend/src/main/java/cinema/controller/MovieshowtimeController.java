package cinema.controller;

import cinema.dto.movieShowTimeDTO.Movie_showtimeCreateRequest;
import cinema.dto.movieShowTimeDTO.Movie_showtimeDTO;
import cinema.dto.movieShowTimeDTO.Movie_showtimeUpdateRequest;
import cinema.dto.reviewDTO.ReviewCreateRequest;
import cinema.dto.reviewDTO.ReviewDTO;
import cinema.dto.reviewDTO.ReviewSearchRequest;
import cinema.dto.reviewDTO.ReviewUpdateRequest;
import cinema.entity.Movie_showtime;
import cinema.entity.Review;
import cinema.form.MovieShowTimeFilterForm;
import cinema.service.IMovieshowtime;
import cinema.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movieshowtime")
@CrossOrigin("*")
public class MovieshowtimeController {
    @Autowired
    private IMovieshowtime movieshowtimeSevice;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all/get-all")
    public List<Movie_showtimeDTO> getAll() {
        List<Movie_showtime> list = movieshowtimeSevice.getAll();
        List<Movie_showtimeDTO> dtos = modelMapper.map(list, new TypeToken<List<Movie_showtimeDTO>>() {}.getType());
        return dtos;
    }

    @PostMapping("/admin/create")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public void create(@RequestBody Movie_showtimeCreateRequest request) {
         movieshowtimeSevice.create(request);
    }

    @GetMapping("/all/{id}")
    public Movie_showtimeDTO getById(@PathVariable int id) {
        Movie_showtime review = movieshowtimeSevice.getById(id);
        Movie_showtimeDTO dto = modelMapper.map(review, Movie_showtimeDTO.class);
        return dto;
    }

    @PutMapping("/admin/update")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public void update(@RequestBody Movie_showtimeUpdateRequest request) {
         movieshowtimeSevice.update(request);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String delete(@PathVariable int id) {
        movieshowtimeSevice.delete(id);
        return "Đã xóa thành công!";
    }

    @PostMapping("/all/search")
    public Page<Movie_showtimeDTO> search(@RequestBody MovieShowTimeFilterForm request, Pageable pageable) {
        Page<Movie_showtime> reviewPage = movieshowtimeSevice.search(request);
        List<Movie_showtimeDTO> dtos = modelMapper.map(reviewPage.getContent(), new TypeToken<List<Movie_showtimeDTO>>() {}.getType());
        Page<Movie_showtimeDTO> dtoPage = new PageImpl<>(dtos, pageable, reviewPage.getTotalElements());
        return dtoPage;
    }

    @GetMapping("/all/find-by-film")
    public List<Movie_showtimeDTO> findByFilm(@RequestParam int filmId) {
        List<Movie_showtime> reviewList = movieshowtimeSevice.findByFilm(filmId);
        List<Movie_showtimeDTO> dtos = modelMapper.map(reviewList, new TypeToken<List<Movie_showtimeDTO>>() {}.getType());
        return dtos;
    }

}

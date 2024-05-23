package cinema.service;

import cinema.dto.FilmDto;
import cinema.entity.Country;
import cinema.entity.Film;
import cinema.entity.Genre;
import cinema.form.FilmCreateForm;
import cinema.form.FilmFilterForm;
import cinema.form.FilmUpdateForm;
import cinema.repository.*;
import cinema.specification.FilmSpecification;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    @Autowired
    private CountryRespository countryRespository;

    @Autowired
    private GenreRespository genreRespository;

    @Autowired
    private MovieshowtimeRepository movieshowtimeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    private ModelMapper modelMapper;

    @Autowired
    public FilmServiceImpl(
            FilmRepository filmRepository,
            ModelMapper modelMapper
    ) {
        this.filmRepository = filmRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<FilmDto> viewFilm(FilmFilterForm form, Pageable pageable) {

        Specification<Film> filmSpec = FilmSpecification.buildSpec(form);
        Page<Film> films = filmRepository.findAll(filmSpec, pageable);
        Page<FilmDto> filmsDto = films.map(film -> modelMapper.map(film, FilmDto.class));

        return filmsDto;

    }


    @Override
    public FilmDto findById(Integer id) {
        FilmDto film = filmRepository.findById(id)
                .map(post -> modelMapper.map(post, FilmDto.class))
                .orElse(null);
        return film;
    }




    @Override
    public FilmDto createFilm(FilmCreateForm form) {

        Film film = modelMapper.map(form, Film.class);
        Country country = countryRespository.findByName(form.getCountryName());
        Genre genre = genreRespository.findByName(form.getGenreName());
        film.setCountry(country);
        film.setGenre(genre);
        Film savedFilm = filmRepository.save(film);
        return modelMapper.map(savedFilm, FilmDto.class);

    }



    @Override
    public FilmDto updateFilm(Integer id, FilmUpdateForm form) {
        Film film = filmRepository.findById(id)
                .orElse(null);
        modelMapper.map(form, film);
        Film savedFilm = filmRepository.save(film);
        return modelMapper.map(savedFilm, FilmDto.class);
    }




    @Override
    public void deleteFilm(Integer id) {
        filmRepository.deleteById(id);
    }

}

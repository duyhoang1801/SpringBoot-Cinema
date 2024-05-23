package cinema.service;

import cinema.dto.FilmDto;
import cinema.form.FilmCreateForm;
import cinema.form.FilmFilterForm;
import cinema.form.FilmUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmService {

    public Page<FilmDto> viewFilm(FilmFilterForm form, Pageable pageable);

    public FilmDto findById(Integer id);

    public FilmDto createFilm(FilmCreateForm form);

    public FilmDto updateFilm(Integer id, FilmUpdateForm form);

    public void deleteFilm(Integer id);
}

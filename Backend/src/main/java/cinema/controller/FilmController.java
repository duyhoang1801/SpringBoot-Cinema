package cinema.controller;

import cinema.dto.FilmDto;
import cinema.form.FilmCreateForm;
import cinema.form.FilmFilterForm;
import cinema.form.FilmUpdateForm;
import cinema.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/film")
@CrossOrigin("*")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping("/all/get-all")
    @ResponseStatus(HttpStatus.OK)
    public Page<FilmDto> ViewAllFilm(
            FilmFilterForm form,
            Pageable pageable)
    {
        Page<FilmDto> filmDto = filmService.viewFilm(form, pageable);
        return filmDto;
    }

    @GetMapping("/all/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmDto findByID(@PathVariable int id) { return filmService.findById(id);}


    @PostMapping("/admin/create")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto createFilm(@RequestBody FilmCreateForm form){
        return filmService.createFilm(form);
    }


    @PutMapping("/admin/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public FilmDto updateFilm(@PathVariable Integer id, @RequestBody FilmUpdateForm form) {
        return filmService.updateFilm(id, form);
    }


    @DeleteMapping("/admin/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFilm(@PathVariable("id") Integer id){
        filmService.deleteFilm(id);
    }







}




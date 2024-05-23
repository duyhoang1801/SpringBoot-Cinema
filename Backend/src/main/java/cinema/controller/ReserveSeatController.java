package cinema.controller;

import cinema.dto.FilmDto;
import cinema.dto.ReserveSeatDto;
import cinema.entity.ReserveSeat;
import cinema.form.FilmCreateForm;
import cinema.form.FilmFilterForm;
import cinema.form.ReserveSeatCreateForm;
import cinema.service.FilmService;
import cinema.service.ReserveSeatService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reserveSeat")
@CrossOrigin("*")
public class ReserveSeatController {
    private ReserveSeatService reserveSeatService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public ReserveSeatController(ReserveSeatService reserveSeatService) {
        this.reserveSeatService = reserveSeatService;
    }


    @GetMapping("/get")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.OK)
    public List<ReserveSeatDto> ViewAll() {
        List<ReserveSeat> entities = reserveSeatService.findAll();
        List<ReserveSeatDto> dtos = modelMapper.map(entities,new TypeToken<List<ReserveSeatDto>>() {}.getType());
        return dtos;
    }



    @PostMapping("/create")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ReserveSeatDto createReserveSeat(@RequestBody ReserveSeatCreateForm form){
        return reserveSeatService.createReserveSeat(form);
    }



    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.OK)
    public List<ReserveSeatDto> ViewByMovieShowTime(@PathVariable("id") int movieShowTimeId) {
        List<ReserveSeat> entities = reserveSeatService.findAllByMovieShowtimeId(movieShowTimeId);
        List<ReserveSeatDto> dtos = modelMapper.map(entities, new TypeToken<List<ReserveSeatDto>>() {}.getType());
        return dtos;
    }
}

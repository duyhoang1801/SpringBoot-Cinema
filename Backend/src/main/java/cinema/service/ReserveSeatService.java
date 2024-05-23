package cinema.service;

import cinema.dto.ReserveSeatDto;
import cinema.entity.ReserveSeat;
import cinema.form.ReserveSeatCreateForm;

import java.util.List;

public interface ReserveSeatService {

    public ReserveSeatDto createReserveSeat(ReserveSeatCreateForm form);

    List<ReserveSeat> findAll();

    List<ReserveSeat> findAllByMovieShowtimeId(int movieShowtimeId);


}

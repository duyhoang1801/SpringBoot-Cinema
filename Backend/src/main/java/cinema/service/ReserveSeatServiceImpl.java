package cinema.service;

import cinema.dto.FilmDto;
import cinema.dto.ReserveSeatDto;
import cinema.entity.Film;
import cinema.entity.ReserveSeat;
import cinema.form.FilmCreateForm;
import cinema.form.ReserveSeatCreateForm;
import cinema.repository.FilmRepository;
import cinema.repository.ReserveSeatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReserveSeatServiceImpl implements ReserveSeatService{

    private ReserveSeatRepository reserveSeatRepository;

    private ModelMapper modelMapper;
    @Autowired
    public ReserveSeatServiceImpl(
            ReserveSeatRepository reserveSeatRepository,
            ModelMapper modelMapper
    ) {
        this.reserveSeatRepository = reserveSeatRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ReserveSeat> findAll() {
        return reserveSeatRepository.findAll();
    }


    @Override
    public ReserveSeatDto createReserveSeat(ReserveSeatCreateForm form) {

        ReserveSeat reserveSeat = modelMapper.map(form, ReserveSeat.class);
        ReserveSeat savedReserveSeat = reserveSeatRepository.save(reserveSeat);
        return modelMapper.map(savedReserveSeat, ReserveSeatDto.class);

    }




    @Override
    public List<ReserveSeat> findAllByMovieShowtimeId(int movieShowtimeId) {
        return reserveSeatRepository.findAllByMovieShowtimeId(movieShowtimeId);
    }
}

package cinema.dto.movieShowTimeDTO;

import cinema.dto.CinemaRoomDTO;
import cinema.dto.FilmDto;
import cinema.dto.TheaterDto;
import cinema.entity.CinemaRoom;
import cinema.entity.Film;
import cinema.entity.Theater;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class Movie_showtimeDTO {
    private int id;
    private LocalDate dateShow;
    private Time timeShow;
    private CinemaRoomDTO cinemaRoom;
    private FilmDto film;


}

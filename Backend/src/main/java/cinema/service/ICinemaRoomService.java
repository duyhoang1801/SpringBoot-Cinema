package cinema.service;

import cinema.entity.CinemaRoom;

import java.util.List;

public interface ICinemaRoomService {
    List<CinemaRoom> getAll();

    CinemaRoom create(CinemaRoom cinemaRoom);

    CinemaRoom update(CinemaRoom cinemaRoom, int id);

    void delete(int id);
}

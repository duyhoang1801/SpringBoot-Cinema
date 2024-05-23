package cinema.service;

import cinema.entity.CinemaRoom;
import cinema.exception.AppException;
import cinema.exception.ErrorResponseEnum;
import cinema.repository.CinemaRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CinemaRoomService implements ICinemaRoomService{
    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;
    @Override
    public List<CinemaRoom> getAll() {
        return cinemaRoomRepository.findAll();
    }

    @Override
    public CinemaRoom create(CinemaRoom cinemaRoom) {
        return cinemaRoomRepository.save(cinemaRoom);
    }

    @Override
    public CinemaRoom update(CinemaRoom cinemaRoom, int id) {
        CinemaRoom exist = cinemaRoomRepository.findById(id).get();
        if (exist != null) {
            exist.setNumberOfRoom(cinemaRoom.getNumberOfRoom());
            exist.setStatus(cinemaRoom.isStatus());
            cinemaRoomRepository.save(exist);
            return exist;
        } else {
            throw new AppException(ErrorResponseEnum.CINEMA_ROOM_NOT_FOUND);
        }
    }

    @Override
    public void delete(int id) {
        Optional<CinemaRoom> cinemaRoomOptional = cinemaRoomRepository.findById(id);
        if (cinemaRoomOptional.isPresent()) {
            cinemaRoomRepository.deleteById(id);
        } else {
            throw new AppException(ErrorResponseEnum.CINEMA_ROOM_NOT_FOUND);
        }
    }
}

package cinema.controller;

import cinema.dto.CinemaRoomDTO;
import cinema.entity.CinemaRoom;
import cinema.service.CinemaRoomService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/room")
public class CinemaRoomController {
    @Autowired
    private CinemaRoomService cinemaRoomService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get-all")
        public List<CinemaRoomDTO> getAll(){
        List<CinemaRoom> entities = cinemaRoomService.getAll();
        List<CinemaRoomDTO> dtos = modelMapper.map(entities, new TypeToken<List<CinemaRoomDTO>>() {}.getType());
        return dtos;
        }

    @PostMapping("/create")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
        public CinemaRoomDTO create(@RequestBody CinemaRoom cinemaRoom){
        CinemaRoom entity = cinemaRoomService.create(cinemaRoom);
        CinemaRoomDTO dto = modelMapper.map(entity, new TypeToken<CinemaRoomDTO>() {}.getType());
        return dto;
    }

    @PutMapping("/update")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public CinemaRoomDTO update(@RequestBody CinemaRoom cinemaRoom, @PathVariable int id){
        CinemaRoom entity = cinemaRoomService.update(cinemaRoom, id);
        CinemaRoomDTO dto = modelMapper.map(entity, new TypeToken<CinemaRoomDTO>() {}.getType());
        return dto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String delete(@PathVariable int id){
        cinemaRoomService.delete(id);
        return "Đã xóa thành công";
    }
}

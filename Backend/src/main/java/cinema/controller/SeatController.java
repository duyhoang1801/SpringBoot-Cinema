package cinema.controller;

import cinema.dto.SeatDTO;
import cinema.entity.Seat;
import cinema.form.SeatSearchForm;
import cinema.service.SeatService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/seat")
@CrossOrigin(origins = "**")
@RestController
@Validated
public class SeatController {
    @Autowired
    private SeatService seatService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/get-by-row")
    public List<SeatDTO> getByRow(@RequestBody SeatSearchForm request) {
        List<Seat> entities = seatService.getSeatByRow(request);
        List<SeatDTO> dtos = modelMapper.map(entities, new TypeToken<List<SeatDTO>>() {}.getType());
        return dtos;
    }
}

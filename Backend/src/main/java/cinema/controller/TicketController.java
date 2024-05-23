package cinema.controller;

import cinema.dto.ReserveSeatDto;
import cinema.dto.TicketCreateForm;
import cinema.dto.TicketDTO;
import cinema.entity.Ticket;
import cinema.form.ReserveSeatCreateForm;
import cinema.service.TicketService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@CrossOrigin("*")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get-all")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDTO> getAll() {
        List<Ticket> entities = ticketService.getAll();
        List<TicketDTO> dtos = modelMapper.map(entities,new TypeToken<List<TicketDTO>>() {}.getType());
        return dtos;
    }

    @PostMapping("/create")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDTO createTicket(@RequestBody TicketCreateForm form){
        return ticketService.create(form);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    public String delete(@PathVariable Integer id) {
        ticketService.delete(id);
        return "Đã xoá thành công";
    }
}

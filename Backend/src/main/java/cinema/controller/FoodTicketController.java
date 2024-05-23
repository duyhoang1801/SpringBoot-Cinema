package cinema.controller;

import cinema.dto.FoodTicketDto;
import cinema.dto.ReserveSeatDto;
import cinema.entity.FoodTicket;
import cinema.form.FoodTicketCreateForm;
import cinema.form.ReserveSeatCreateForm;
import cinema.service.FoodTicketService;
import cinema.service.ReserveSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/foodTicket")
@CrossOrigin("*")
public class FoodTicketController {


    private FoodTicketService foodTicketService;

    @Autowired
    public FoodTicketController(FoodTicketService foodTicketService) {
        this.foodTicketService = foodTicketService;
    }



    @PostMapping("/create")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public FoodTicketDto createReserveSeat(@RequestBody FoodTicketCreateForm form){
        return foodTicketService.createFoodTicket(form);
    }


    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodTicketDto> findByTicketId(@PathVariable("id") Integer ticketId) {
        return foodTicketService.findByTicketId(ticketId);
    }
}

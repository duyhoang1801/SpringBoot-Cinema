package cinema.service;

import cinema.dto.FoodTicketDto;
import cinema.entity.FoodTicket;
import cinema.form.FoodTicketCreateForm;

import java.util.List;

public interface FoodTicketService {

    public FoodTicketDto createFoodTicket(FoodTicketCreateForm form);
    List<FoodTicketDto> findByTicketId(Integer ticketId);
}

package cinema.service;

import cinema.dto.TicketCreateForm;
import cinema.dto.TicketDTO;
import cinema.entity.FoodType;
import cinema.entity.Ticket;
import io.swagger.models.auth.In;

import java.util.List;

public interface ITicket {
    List<Ticket> getAll();

    public TicketDTO create(TicketCreateForm form);

    void delete(Integer id);
}

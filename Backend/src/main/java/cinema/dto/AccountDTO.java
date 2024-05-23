package cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class AccountDTO {
    private int accountId;

    private String fullName;

    private String role;

    private String email;

    private String avatar;

    private String password;

    private String gender;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private List<TicketDTO> tickets;

    @Data
    @NoArgsConstructor
    public static class TicketDTO {
        @JsonProperty("id")
        private Integer id;

        private BigDecimal price;

        private List<FoodTicketDto> foodTickets;

        private List<ReserveSeatDto> reserveSeat;
    }
}

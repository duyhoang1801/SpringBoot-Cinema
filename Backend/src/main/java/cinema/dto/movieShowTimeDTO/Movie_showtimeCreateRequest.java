package cinema.dto.movieShowTimeDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class Movie_showtimeCreateRequest {
    @NotBlank(message = "Không được để trống")
    private LocalDate dateShow;

    @NotBlank(message = "Không được để trống")
    private Time timeShow;

    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải lớn hơn 0")
    private int cinemaRoomID;

    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải lớn hơn 0")
    private int filmId;

}

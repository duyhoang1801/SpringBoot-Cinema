package cinema.dto.movieShowTimeDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class Movie_showtimeUpdateRequest {
    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải lớn hơn 0")
    private int id;

    @NotBlank(message = "Không được để trống")
    private Date dateShow;

    @NotBlank(message = "Không được để trống")
    private Time timeShow;

    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải lớn hơn 0")
    private int cinemaRoomID;

    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải lớn hơn 0")
    private int filmId;
}

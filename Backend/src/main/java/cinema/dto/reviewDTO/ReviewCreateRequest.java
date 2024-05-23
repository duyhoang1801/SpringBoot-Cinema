package cinema.dto.reviewDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ReviewCreateRequest {
    @NotBlank(message = "Không được để trống nội dung")
    private String content;

    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải lớn hơn 0")
    private int accountId;

    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải lớn hơn 0")
    private int filmId;
}

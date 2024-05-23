package cinema.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
public class FoodCreateRequest {
    @Length(max = 50, message = "Tên không được quá 50 ký tự")
    private String nameFood;

    @NotNull(message = "Id không được để trống")
    @Positive(message = "Id phải lớn hơn 0")
    private int foodTypeId;

    @NotNull
    private BigDecimal price;
}

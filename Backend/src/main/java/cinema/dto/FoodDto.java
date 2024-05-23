package cinema.dto;

import cinema.entity.FoodType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class FoodDto {

    private int foodId;

    private String nameFood;

    private FoodType foodType;

    private BigDecimal price;
}

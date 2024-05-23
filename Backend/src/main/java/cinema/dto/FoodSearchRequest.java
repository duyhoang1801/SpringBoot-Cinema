package cinema.dto;

import lombok.Data;

@Data
public class FoodSearchRequest extends BaseRequest{
    private String nameFood;
    private int foodTypeId;
}

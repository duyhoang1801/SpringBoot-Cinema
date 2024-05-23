package cinema.form;

import cinema.dto.reviewDTO.BaseRequest;
import lombok.Data;

import java.util.Date;

@Data
public class MovieShowTimeFilterForm extends BaseRequest {
    private int filmId;
}

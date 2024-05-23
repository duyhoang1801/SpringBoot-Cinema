package cinema.dto.reviewDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReviewSearchRequest extends BaseRequest {
    private int accountId;
    private int filmId;
}

package cinema.dto.reviewDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDTO {
    private int id;
    private String content;
    private String accountFullName;
    private String filmName;
}

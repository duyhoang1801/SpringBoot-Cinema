package cinema.dto;

import cinema.entity.Theater;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CinemaRoomDTO {
    private int cinemaRoomId;

    private int numberOfRoom;

    private boolean status;

    private TheaterDto theater;

    private List<SeatDTO> seat;

    @Data
    @NoArgsConstructor
    public static class SeatDTO {
        private Integer id;

        private Character row;

        private Integer column;

        private Boolean status;
    }
}

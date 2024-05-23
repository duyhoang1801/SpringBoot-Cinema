package cinema.specification;

import cinema.dto.AccountSearchRequest;
import cinema.entity.*;
import cinema.form.FilmCreateForm;
import cinema.form.SeatSearchForm;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class SeatSpecification {
    public static Specification<Seat> buildCondition(SeatSearchForm request) {
        return Specification.where(buildConditionCinemaRoomId(request)).and(buildConditionRow(request));}

        public static Specification<Seat> buildConditionCinemaRoomId(SeatSearchForm request) {
            if (request.getCinemaRoomCinemaRoomID() != 0) {
                return (root, query, criteriaBuilder) -> {
                    Join<Seat, CinemaRoom> join = root.join("cinemaRoom");
                    return criteriaBuilder.equal(join.get("id"), request.getCinemaRoomCinemaRoomID());
                };
            } else {
                return null;
            }
        }
    public static Specification<Seat> buildConditionRow(SeatSearchForm request) {
        if (request.getRow() != null) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("row"), "%" + request.getRow() + "%");
            };
        } else {
            return null;
        }
    }
    }

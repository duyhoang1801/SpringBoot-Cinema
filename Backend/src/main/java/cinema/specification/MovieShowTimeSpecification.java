package cinema.specification;

import cinema.dto.reviewDTO.ReviewSearchRequest;
import cinema.entity.Account;
import cinema.entity.Film;
import cinema.entity.Movie_showtime;

import cinema.form.FilmFilterForm;
import cinema.form.MovieShowTimeFilterForm;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieShowTimeSpecification {

        public static Specification<Movie_showtime> buildCondition(MovieShowTimeFilterForm request) {
            return Specification.where(buildConditionFilm(request));
        }

//    public static Specification<Review> buildConditionRating(ReviewSearchRequest request) {
//        if (request.getRating() > 0) {
//            return (root, query, criteriaBuilder) -> {
//                return criteriaBuilder.equal(root.get("rating"), request.getRating());
//            };
//        } else {
//            return null;
//        }
//    }

        public static Specification<Movie_showtime> buildConditionFilm(MovieShowTimeFilterForm request) {
            if (request.getFilmId() > 0) {
                return (root, query, criteriaBuilder) -> {
                    Join<Movie_showtime, Film> join = root.join("film");
                    return criteriaBuilder.equal(join.get("id"), request.getFilmId());
                };
            } else {
                return null;
            }
        }
}

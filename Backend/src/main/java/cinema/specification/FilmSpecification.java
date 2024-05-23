package cinema.specification;

import cinema.entity.Film;
import cinema.form.FilmFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.util.TypeUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FilmSpecification  {

    public static Specification<Film> buildSpec(FilmFilterForm form) {

        return form == null ? null : new Specification<Film>() { //--> ở đây đã return 1 đối tượng có kiểu dữ liệu Specification<Film>() rồi
            @Override
            public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>(); //--> Tạo danh sách điều kiện

                String name = form.getName();
                if (StringUtils.hasText(name)) {
                    String pattern = "%" + name.trim() + "%";
                    Predicate hasNameLike = builder.like(root.get("name"), pattern);

                    predicates.add(hasNameLike);
                }

                String country = form.getCountry();
                if (StringUtils.hasText(country)) {
                    String pattern = "%" + country.trim() + "%";
                    Predicate hasCountryLike = builder.like(root.get("country"), pattern);

                    predicates.add(hasCountryLike);
                }

                String genre = form.getGenre();
                if (StringUtils.hasText(genre)) {
                    String pattern = "%" + genre.trim() + "%";
                    Predicate hasGenreLike = builder.like(root.get("genre"), pattern);

                    predicates.add(hasGenreLike);
                }

                return builder.and(predicates.toArray(new Predicate[0]));
            }

        };
    }


}

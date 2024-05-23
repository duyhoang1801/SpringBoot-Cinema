package cinema.specification;

import cinema.entity.Film;
import cinema.entity.Theater;
import cinema.form.FilmFilterForm;
import cinema.form.TheaterFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TheaterSpecification {
    public static Specification<Theater> buildSpec(TheaterFilterForm form) {

        return form == null ? null : new Specification<Theater>() { //--> ở đây đã return 1 đối tượng có kiểu dữ liệu Specification<Film>() rồi
            @Override
            public Predicate toPredicate(Root<Theater> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>(); //--> Tạo danh sách điều kiện

                String name = form.getName();
                if (StringUtils.hasText(name)) {
                    String pattern = "%" + name.trim() + "%";
                    Predicate hasNameLike = builder.like(root.get("name"), pattern);

                    predicates.add(hasNameLike);
                }


                return builder.and(predicates.toArray(new Predicate[0]));
            }

        };
    }

}

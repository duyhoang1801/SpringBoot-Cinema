package cinema.specification;

import cinema.dto.FoodSearchRequest;
import cinema.entity.Food;
import cinema.entity.FoodType;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class FoodSpecification {
    public static Specification<Food> buildCondition(FoodSearchRequest request) {
        return Specification.where(buildConditionName(request)).and(buildConditionCategory(request));
    }
    public static Specification<Food> buildConditionName(FoodSearchRequest request) {
        if (request.getNameFood() != null && request.getNameFood() != "") {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("nameFood"), "%" + request.getNameFood() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<Food> buildConditionCategory(FoodSearchRequest request) {
        if (request.getFoodTypeId() > 0) {
            return (root, query, criteriaBuilder) -> {
                Join<Food, FoodType> join = root.join("category");
                return criteriaBuilder.equal(join.get("id"), request.getFoodTypeId());
            };
        } else {
            return null;
        }
    }
}

package cinema.specification;

import cinema.dto.AccountSearchRequest;
import cinema.entity.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<Account> buildCondition(AccountSearchRequest request) {
        return Specification.where(buildConditionPhoneNumber(request)).and(buildConditionFullName(request))
                .and(buildConditionEmail(request));
    }

    public static Specification<Account> buildConditionPhoneNumber(AccountSearchRequest request) {
        if (request.getPhoneNumber() != null && request.getPhoneNumber() != "") {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("phoneNumber"), "%" + request.getPhoneNumber() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<Account> buildConditionFullName(AccountSearchRequest request) {
        if (request.getFullName() != null && request.getFullName() != "") {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("fullName"), "%" + request.getFullName() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<Account> buildConditionEmail(AccountSearchRequest request) {
        if (request.getEmail() != null && request.getEmail() != "") {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%");
            };
        } else {
            return null;
        }
    }
}

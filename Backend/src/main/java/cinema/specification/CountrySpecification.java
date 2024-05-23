//package cinema.specification;
//
//import cinema.dto.AccountSearchRequest;
//import cinema.entity.Country;
//import cinema.form.FilmCreateForm;
//import org.springframework.data.jpa.domain.Specification;
//
//public class CountrySpecification {
//    public static Specification<Country> buildCondition(FilmCreateForm request) {
//        return Specification.where(buildConditionPhoneNumber(request)).and(buildConditionFullName(request))
//                .and(buildConditionEmail(request));
//    }
//}

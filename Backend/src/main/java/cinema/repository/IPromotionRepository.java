package cinema.repository;

import cinema.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IPromotionRepository extends JpaRepository<Promotion, Integer>, JpaSpecificationExecutor<Promotion> {

}

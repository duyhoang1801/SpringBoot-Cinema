package cinema.repository;

import cinema.entity.Account;
import cinema.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer>, JpaSpecificationExecutor<Food> {

//    List<Food> findAllByFoodType_Id(int foodTypeId);

//     List<Food> findAllByFoodType_Id(int foodTypeId);



}

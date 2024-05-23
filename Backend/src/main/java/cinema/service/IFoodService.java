package cinema.service;

import cinema.dto.FoodCreateRequest;
import cinema.dto.FoodSearchRequest;
import cinema.dto.FoodUpdateRequest;
import cinema.entity.Food;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IFoodService {
    List<Food> getAll();

    Food create(FoodCreateRequest request);

    Food update(FoodUpdateRequest request);

    Food getById(int id);

    void delete(int id);

    Page<Food> search(FoodSearchRequest request);


//     List<Food> findByFoodType(int foodTypeId);

//     List<Food> findAllByFoodType(int foodTypeId);




}

package cinema.service;

import cinema.entity.FoodType;

import java.util.List;

public interface IFoodTypeService {
    List<FoodType> getAll();

    FoodType create(FoodType foodType);

    FoodType update(FoodType foodType, int id);

    void delete(int id);
}

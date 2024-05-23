package cinema.service;

import cinema.entity.CinemaRoom;
import cinema.entity.Food;
import cinema.entity.FoodType;
import cinema.exception.AppException;
import cinema.exception.ErrorResponseEnum;
import cinema.repository.FoodTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FoodTypeService implements IFoodTypeService{
    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Override
    public List<FoodType> getAll() {
        return foodTypeRepository.findAll();
    }

    @Override
    public FoodType create(FoodType foodType) {
        return foodTypeRepository.save(foodType);
    }

    @Override
    public FoodType update(FoodType foodType, int id) {
        FoodType exist = foodTypeRepository.findById(id).get();
        if (exist != null) {
            exist.setCategoryFood(foodType.getCategoryFood());
            foodTypeRepository.save(exist);
            return exist;
        } else {
            throw new AppException(ErrorResponseEnum.FOOD_TYPE_NOT_FOUND);
        }
    }

    @Override
    public void delete(int id) {
        Optional<FoodType> foodTypeOptional = foodTypeRepository.findById(id);
        if (foodTypeOptional.isPresent()) {
            foodTypeRepository.deleteById(id);
        } else {
            throw new AppException(ErrorResponseEnum.FOOD_TYPE_NOT_FOUND);
        }
    }
}

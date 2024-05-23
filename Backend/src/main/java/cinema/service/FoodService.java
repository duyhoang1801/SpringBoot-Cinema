package cinema.service;

import cinema.dto.FoodCreateRequest;
import cinema.dto.FoodSearchRequest;
import cinema.dto.FoodUpdateRequest;
import cinema.entity.CinemaRoom;
import cinema.entity.Food;
import cinema.entity.FoodType;
import cinema.exception.AppException;
import cinema.exception.ErrorResponseEnum;
import cinema.repository.FoodRepository;
import cinema.repository.FoodTypeRepository;
import cinema.specification.FoodSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FoodService implements IFoodService{
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Override
    public List<Food> getAll() {
        return foodRepository.findAll();
    }

    @Override
    public Food create(FoodCreateRequest request) {
        Optional<FoodType> foodTypeOptional = foodTypeRepository.findById(request.getFoodTypeId());
        if(foodTypeOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.FOOD_TYPE_NOT_FOUND);
        }
        FoodType foodType = foodTypeOptional.get();
        Food food = new Food();
        food.setFoodType(foodType);
        food.setNameFood(request.getNameFood());
        food.setPrice(request.getPrice());
        food = foodRepository.save(food);
        return food;
    }


    @Override
    public Food getById(int id) {
        Optional<Food> foodOptional = foodRepository.findById(id);
        if(foodOptional.isPresent()){
            return foodOptional.get();
        } else {
            throw new AppException(ErrorResponseEnum.FOOD_NOT_FOUND);
        }
    }

    @Override
    public Food update(FoodUpdateRequest request) {
        Optional<FoodType> foodTypeOptional = foodTypeRepository.findById(request.getFoodTypeId());
        if(foodTypeOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.FOOD_TYPE_NOT_FOUND);
        }
        Food food = getById(request.getId());
        if (food != null) {
            FoodType foodType = foodTypeOptional.get();
            BeanUtils.copyProperties(request, food);
            food.setFoodType(foodType);
            food = foodRepository.save(food);
            return food;
        } else {
            throw new AppException(ErrorResponseEnum.FOOD_NOT_FOUND);
        }
    }


    @Override
    public void delete(int id) {
        Optional<Food> accountOptional = foodRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.FOOD_NOT_FOUND);
        } else {
            foodRepository.deleteById(id);
        }
    }

    @Override
    public Page<Food> search(FoodSearchRequest request) {
        PageRequest pageRequest = null;
        if ("DESC".equals(request.getSortType())) {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        }
        Specification<Food> condition = FoodSpecification.buildCondition(request);
        return foodRepository.findAll(condition, pageRequest);
    }


    //     @Override
//     public List<Food> findByFoodType(int foodTypeId) {
//         return foodRepository.findAllByFoodType_Id(foodTypeId);
//     }

//     @Override
//     public List<Food> findAllByFoodType(int foodTypeId) {
//         return foodRepository.findAllByFoodType_Id(foodTypeId);
//     }




}

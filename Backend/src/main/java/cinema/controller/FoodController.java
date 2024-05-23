package cinema.controller;

import cinema.dto.*;
import cinema.entity.Food;
import cinema.service.FoodService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
@CrossOrigin("*")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get-all")
    public List<FoodDto> getAll() {
        List<Food> entities = foodService.getAll();
        List<FoodDto> dtos = modelMapper.map(entities, new TypeToken<List<FoodDto>>() {}.getType());
        return dtos;
    }

    @PostMapping("/admin/create")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public FoodDto create(@RequestBody FoodCreateRequest request) {
        Food entities = foodService.create(request);
        FoodDto dto = modelMapper.map(entities, new TypeToken<FoodDto>() {}.getType());
        return dto;
    }

    @GetMapping("/{id}")
    public FoodDto getById(@PathVariable int id) {
        Food entity = foodService.getById(id);
        FoodDto dto = modelMapper.map(entity, new TypeToken<FoodDto>() {}.getType());
        return dto;
    }



    @PutMapping("/admin/update")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public FoodDto update(@RequestBody FoodUpdateRequest request) {
        Food entity = foodService.update(request);
        FoodDto dto = modelMapper.map(entity, new TypeToken<FoodDto>() {}.getType());
        return dto;
    }

    @DeleteMapping("/admin/delete/{id}")
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    public String delete(@PathVariable int id) {
        foodService.delete(id);
        return "Đã xóa thành công!";
    }

    @PostMapping("/search")
    public Page<FoodDto> search(@RequestBody FoodSearchRequest request) {
        Page<Food> entities = foodService.search(request);
        Page<FoodDto> dtos = entities.map(p -> modelMapper.map(p,FoodDto.class));
        return dtos;
    }

//    @GetMapping("find-by-food-type")
//    public List<Food> findByFoodType(@RequestParam int foodTypeId) {
//        return foodService.findByFoodType(foodTypeId);
//    }
}

package cinema.controller;

import cinema.entity.CinemaRoom;
import cinema.entity.FoodType;
import cinema.service.CinemaRoomService;
import cinema.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foodtype")
@CrossOrigin("*")
public class FoodTypeController {
    @Autowired
    private FoodTypeService foodTypeService;

    @GetMapping("/get-all")
    public List<FoodType> getAll(){
        return foodTypeService.getAll();
    }

    @PostMapping("/admin/create")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public FoodType create(@RequestBody FoodType foodType){
        return foodTypeService.create(foodType);
    }

    @PutMapping("/admin/update")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public FoodType update(@RequestBody FoodType foodType, @PathVariable int id){
        return foodTypeService.update(foodType, id);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String delete(@PathVariable int id){
        foodTypeService.delete(id);
        return "Đã xóa thành công";
    }
}

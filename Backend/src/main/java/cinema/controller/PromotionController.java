package cinema.controller;

import cinema.dto.PromotionDTO.PromotionCreateRequest;
import cinema.dto.PromotionDTO.PromotionDTO;
import cinema.dto.PromotionDTO.PromotionUpdateRequest;
import cinema.dto.reviewDTO.ReviewCreateRequest;
import cinema.dto.reviewDTO.ReviewDTO;
import cinema.dto.reviewDTO.ReviewUpdateRequest;
import cinema.entity.Promotion;
import cinema.entity.Review;
import cinema.service.IPromotionSevice;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotion")
@CrossOrigin("*")
public class PromotionController {
    @Autowired
    private IPromotionSevice promotionService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all/get-all")
    public List<PromotionDTO> getAll() {
        List<Promotion> list = promotionService.getAll();
        List<PromotionDTO> dtos = modelMapper.map(list, new TypeToken<List<PromotionDTO>>() {
        }.getType());
        return dtos;
    }




    @PostMapping("/admin/create")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public void create(@RequestBody PromotionCreateRequest request) {
        promotionService.create(request);
    }




    @GetMapping("/all/{id}")
    public PromotionDTO getById(@PathVariable int id) {
        Promotion promotion = promotionService.getById(id);
        PromotionDTO dto = modelMapper.map(promotion, PromotionDTO.class);
        return dto;
    }

    @PutMapping("/admin/update")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public void update(@RequestBody PromotionUpdateRequest request) {
        promotionService.update(request);
    }




    @DeleteMapping("/admin/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String delete(@PathVariable int id) {
        promotionService.delete(id);
        return "Đã xóa thành công!";
    }
}
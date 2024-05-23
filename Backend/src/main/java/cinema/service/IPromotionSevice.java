package cinema.service;

import cinema.dto.PromotionDTO.PromotionCreateRequest;
import cinema.dto.PromotionDTO.PromotionDTO;
import cinema.dto.PromotionDTO.PromotionUpdateRequest;
import cinema.entity.Promotion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IPromotionSevice {
    public List<Promotion> getAll();
    public void create(PromotionCreateRequest request);

    public Promotion getById(int id);

    public void update(PromotionUpdateRequest request);

    public void delete( int id);
}

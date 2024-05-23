package cinema.service;

import cinema.dto.PromotionDTO.PromotionCreateRequest;
import cinema.dto.PromotionDTO.PromotionDTO;
import cinema.dto.PromotionDTO.PromotionUpdateRequest;
import cinema.entity.Account;
import cinema.entity.Film;
import cinema.entity.Promotion;
import cinema.entity.Review;
import cinema.exception.AppException;
import cinema.exception.ErrorResponseEnum;
import cinema.repository.AccountRepository;
import cinema.repository.FilmRepository;
import cinema.repository.IPromotionRepository;
import cinema.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PromotionSevice implements IPromotionSevice {
    @Autowired
    private IPromotionRepository promotionRepository;

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }

    @Override
    public void create(PromotionCreateRequest request) {

        Promotion promotion = new Promotion();
        promotion.setCode(request.getCode());
        promotion.setDiscount(request.getDiscount());
        promotion.setImage(request.getImage());
        promotion.setContent(request.getContent());
        promotion.setExpiration_date(request.getExpiration_date());
        promotionRepository.save(promotion);
    }

    @Override
    public Promotion getById(int id) {
        Optional<Promotion> promotionOptional = promotionRepository.findById(id);
        if (promotionOptional.isPresent()) {
            return promotionOptional.get();
        } else {
            throw new AppException(ErrorResponseEnum.PROMOTION_NOT_FOUND);
        }
    }

    @Override
    public void update(PromotionUpdateRequest request) {
        Promotion promotion = getById(request.getId());
        if (promotion != null) {
            BeanUtils.copyProperties(request, promotion);
            promotionRepository.save(promotion);
        } else {
            throw new AppException(ErrorResponseEnum.PROMOTION_NOT_FOUND);
        }
    }

    @Override
    public void delete(int id) {
        Optional<Promotion> reviewOptional = promotionRepository.findById(id);
        if (reviewOptional.isPresent()) {
            promotionRepository.deleteById(id);
        } else {
            throw new AppException(ErrorResponseEnum.REVIEW_NOT_FOUND);
        }
    }
}

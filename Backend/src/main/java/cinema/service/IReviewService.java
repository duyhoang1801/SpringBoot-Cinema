package cinema.service;

import cinema.dto.reviewDTO.ReviewCreateRequest;
import cinema.dto.reviewDTO.ReviewDTO;
import cinema.dto.reviewDTO.ReviewSearchRequest;
import cinema.dto.reviewDTO.ReviewUpdateRequest;
import cinema.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IReviewService {
    List<ReviewDTO> getAll();

    void create(ReviewCreateRequest request);

    Review getById(int id);

    void update(ReviewUpdateRequest request);

    void delete(int id);
//
    Page<Review> search(ReviewSearchRequest request);
//
    List<Review> findByFilm(int filmId);

    List<Review> findByAccount(int accountId);
}

package cinema.service;

import cinema.dto.reviewDTO.ReviewCreateRequest;
import cinema.dto.reviewDTO.ReviewDTO;
import cinema.dto.reviewDTO.ReviewSearchRequest;
import cinema.dto.reviewDTO.ReviewUpdateRequest;
import cinema.entity.Account;
import cinema.entity.Film;
import cinema.entity.Review;
import cinema.exception.AppException;
import cinema.exception.ErrorResponseEnum;
import cinema.repository.AccountRepository;
import cinema.repository.FilmRepository;
import cinema.repository.ReviewRepository;
import cinema.specification.ReviewSpecification;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<ReviewDTO> getAll() {

        List<Review> reviews = reviewRepository.findAll();
        var dtos = new ArrayList<ReviewDTO>();
        for (Review review : reviews) {
            var dto = modelMapper.map(review, ReviewDTO.class);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void create(ReviewCreateRequest request) {
        Optional<Account> accountOptional = accountRepository.findById(request.getAccountId());
        Optional<Film> filmOptional = filmRepository.findById(request.getFilmId());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.ACCOUNT_NOT_FOUND);
        }
        if (filmOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.FILM_NOT_FOUND);
        }
        Account account = accountOptional.get();
        Film film = filmOptional.get();

        Review review = new Review();
        review.setAccount(account);
        review.setFilm(film);
        review.setContent(request.getContent());
        reviewRepository.save(review);
//        updateRatingFilm(film);
    }

//    private void updateRatingFilm(Film film) {
//        try {
//            film.setRating(reviewRepository.abc(film));
//        } catch (Exception e) {
//            film.setRating(0);
//        }
//    }

    @Override
    public Review getById(int id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            return reviewOptional.get();
        } else {
            throw new AppException(ErrorResponseEnum.REVIEW_NOT_FOUND);
        }
    }

    @Override
    public void update(ReviewUpdateRequest request) {
        Review review = getById(request.getId());
        if (review != null) {
            BeanUtils.copyProperties(request, review);
            review = reviewRepository.save(review);
//            updateRatingFilm(review.getFilm());
        } else {
            throw new AppException(ErrorResponseEnum.REVIEW_NOT_FOUND);
        }
    }

    @Override
    public void delete(int id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            reviewRepository.deleteById(id);
//            updateRatingFilm(reviewOptional.get().getFilm());
        } else {
            throw new AppException(ErrorResponseEnum.REVIEW_NOT_FOUND);
        }
    }

    @Override
    public Page<Review> search(ReviewSearchRequest request) {
        PageRequest pageRequest = null;
        if ("DESC".equals(request.getSortType())) {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());
        }
        Specification<Review> condition = ReviewSpecification.buildCondition(request);
        return reviewRepository.findAll(condition, pageRequest);
    }







    @Override
    public List<Review> findByFilm(int filmId) {
        return reviewRepository.findAllByFilmId(filmId);
    }

    public List<Review> findByAccount(int accountId) {
        return reviewRepository.findAllByAccountAccountId(accountId);
    }
}

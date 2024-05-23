package cinema.controller;

import cinema.dto.reviewDTO.ReviewCreateRequest;
import cinema.dto.reviewDTO.ReviewDTO;
import cinema.dto.reviewDTO.ReviewSearchRequest;
import cinema.dto.reviewDTO.ReviewUpdateRequest;
import cinema.entity.Review;
import cinema.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/review")
@CrossOrigin("*")
public class ReviewController {
    @Autowired
    private IReviewService reviewService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all/get-all")
    public List<ReviewDTO> getAll() {
        List<ReviewDTO> reviewList = reviewService.getAll();
        return reviewList;
    }

    @PostMapping("/user/create")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    public void create(@RequestBody ReviewCreateRequest request) {
        reviewService.create(request);
    }

    @GetMapping("/all/{id}")
    public ReviewDTO getById(@PathVariable int id) {
        Review review = reviewService.getById(id);
        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
        return reviewDTO;
    }



    @PutMapping("/user/update")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    public void update(@RequestBody ReviewUpdateRequest request) {
         reviewService.update(request);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    public String delete(@PathVariable int id) {
        reviewService.delete(id);
        return "Đã xóa thành công!";
    }

    @PostMapping("/all/search")
    public Page<ReviewDTO> search(@RequestBody ReviewSearchRequest request,Pageable pageable) {
        Page<Review> reviewPage = reviewService.search(request);
        List<ReviewDTO> dtos = modelMapper.map(reviewPage.getContent(), new TypeToken<List<ReviewDTO>>() {}.getType());
        Page<ReviewDTO> dtoPage = new PageImpl<>(dtos, pageable, reviewPage.getTotalElements());
        return dtoPage;
    }





    @GetMapping("/all/find-by-film")
    public List<ReviewDTO> findByFilm(@RequestParam int filmId) {
        List<Review> reviewList = reviewService.findByFilm(filmId);
        List<ReviewDTO> dtos = modelMapper.map(reviewList, new TypeToken<List<ReviewDTO>>() {}.getType());
        return dtos;
    }




    @GetMapping("/user/find-by-account")
    @PreAuthorize(value = "hasAuthority('ADMIN', 'USER')")
    public List<ReviewDTO> findByAccount(@RequestParam int accountId) {
        List<Review> reviewList = reviewService.findByAccount(accountId);
        List<ReviewDTO> dtos = modelMapper.map(reviewList, new TypeToken<List<ReviewDTO>>() {}.getType());
        return dtos;
    }
}

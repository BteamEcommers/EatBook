package eBook.EatBook.domain.review.controller;

import eBook.EatBook.domain.review.DTO.ReviewForm;
import eBook.EatBook.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

//    @PostMapping("/create")
//    public String reviewCreate(@Valid ReviewForm reviewForm){
//
//    }
}

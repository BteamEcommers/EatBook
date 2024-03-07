package eBook.EatBook.domain.review.controller;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.review.DTO.ReviewForm;
import eBook.EatBook.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String reviewCreate(@Valid ReviewForm reviewForm, Principal principal){
        Member author =  this.memberService.findByUsername(principal.getName());
        this.reviewService.create(reviewForm, author);


        return String.format("redirect:/book/detail/%d", reviewForm.getBookId());
    }


}

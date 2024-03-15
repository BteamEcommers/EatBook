package eBook.EatBook.domain.review.controller;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.review.DTO.ReportForm;
import eBook.EatBook.domain.review.DTO.ReviewForm;
import eBook.EatBook.domain.review.entity.Review;
import eBook.EatBook.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

        private final ReviewService reviewService;
        private final MemberService memberService;
        private final BookService bookService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String reviewCreate(@PathVariable("id") Integer id, @Valid ReviewForm reviewForm,
                               BindingResult bindingResult, Principal principal, org.springframework.ui.Model model){
        Book books = this.bookService.getBookById(id);
        if (books == null) {
            // 책이 존재하지 않는 경우에 대한 처리
            return "redirect:/books/list"; // 예시로 책 목록 페이지로 리다이렉트
        }

        float rating = reviewForm.getRating();

        if (bindingResult.hasErrors()) {
            model.addAttribute("books",books);
            return "book_detail";
        }
        Member member =  this.memberService.findByUsername(principal.getName());
        if (member == null) {
            // 작성자를 찾을 수 없는 경우에 대한 처리
            return "redirect:/book/list"; // 예시로 홈 페이지로 리다이렉트
        }
        this.reviewService.create(books,reviewForm.getContent(), member,rating);
        return String.format("redirect:/book/detail/%d", id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String reviewModify(@PathVariable("id") Integer id,@Valid ReviewForm reviewForm,
                               BindingResult bindingResult, Principal principal){

        Review review = this.reviewService.getReview(id);
        if (review == null) {
            // 책이 존재하지 않는 경우에 대한 처리
            return "redirect:/book/list"; // 예시로 책 목록 페이지로 리다이렉트
        }

        float rating = reviewForm.getRating();

        if (bindingResult.hasErrors()) {

            return "book_detail";
        }
        Member author = review.getAuthor();
        // 현재 로그인한 사용자가 해당 리뷰의 작성자인지 확인
        if (!principal.getName().equals(author.getUsername())) {
            // 작성자와 다른 사용자가 수정을 시도한 경우에 대한 처리
            return "redirect:/books/list"; // 예시로 책 목록 페이지로 리다이렉트
        }
        review.setContent(reviewForm.getContent());
        this.reviewService.modify(review, reviewForm.getContent(),rating);
        return String.format("redirect:/book/detail/%s",review.getBook().getId());
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String reviewDelete(Principal principal, @PathVariable("id") Integer id) {
        Review review = this.reviewService.getReview(id);

        Member member =  this.memberService.findByUsername(principal.getName());
        if (member == null) {
            // 작성자를 찾을 수 없는 경우에 대한 처리
            return "redirect:/book/list"; // 예시로 홈 페이지로 리다이렉트
        }

        this.reviewService.delete(review);

        return String.format("redirect:/book/detail/%s",review.getBook().getId());
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/report/{id}")
    public String reportComment(@PathVariable("id") Integer id, Principal principal,
                                @Valid ReportForm reportForm, BindingResult bindingResult)  {

        Integer reviewId = reportForm.getReviewId();
        Review review = this.reviewService.getReview(reviewId);

        if (bindingResult.hasErrors()) {
            return "book_detail";
        }
        Member member = this.memberService.getMember(principal.getName());

        String reportType = determineReportType(reportForm.getReportType());

        this.reviewService.report(review, reportForm.getReportContent(), member,reportType);

        return String.format("redirect:/book/detail/%s",review.getBook().getId());
    }
    private String determineReportType(String selectedValue) {

        if ("욕설".equals(selectedValue)) {
            return "욕설";
        } else if ("스팸 및 광고".equals(selectedValue)) {
            return "스팸 및 광고";
        } else if ("불법적인 콘텐츠".equals(selectedValue)) {
            return "불법적인 콘텐츠";
        }
        return "기타";
    }
}

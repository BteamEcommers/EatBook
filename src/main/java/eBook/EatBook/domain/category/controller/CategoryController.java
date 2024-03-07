package eBook.EatBook.domain.category.controller;


import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import eBook.EatBook.domain.category.service.CategoryService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;
    private final MemberService memberService;
    private final ReviewService reviewService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String reviewCreate(@PathVariable("id") Integer id, @RequestParam(value = "content")String content,
                               BindingResult bindingResult, Principal principal, org.springframework.ui.Model model){
        Book books = this.bookService.getBookById(id);
        if (books == null) {
            // 책이 존재하지 않는 경우에 대한 처리
            return "redirect:/books/list"; // 예시로 책 목록 페이지로 리다이렉트
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("books",books);
            return "book_detail";
        }

        Member author =  this.memberService.findByUsername(principal.getName());
        if (author == null) {
            // 작성자를 찾을 수 없는 경우에 대한 처리
            return "redirect:/book/list"; // 예시로 홈 페이지로 리다이렉트
        }
        this.reviewService.create(books,content, author);

        return String.format("redirect:/book/detail/%d", id);
    }
    @GetMapping("/modify/{id}")
    public String reviewModify(@PathVariable("id") Integer id, Model model){
        Book book = this.bookService.getBookById(id);
        model.addAttribute("book",book);
        return "review_modify_form";
    }
    @PostMapping("/modify")
    public String reviewModify(@Valid ReviewForm reviewForm, BindingResult bindingResult, Principal principal){

        if (bindingResult.hasErrors()) {
            return "book_detail";
        }
        Book book = this.bookService.getBookById(reviewForm.getBookId());

        this.reviewService.modify(book, reviewForm.getContent());
        return String.format("redirect:/book/detail/%d", reviewForm.getBookId());
    }
    @GetMapping("/delete/{id}")
    public String reviewDelete(Principal principal, @PathVariable("id") Integer id) {
        Review review = this.reviewService.getReview(id);

        this.reviewService.delete(review);

        return "redirect:/book/list";
    }
}





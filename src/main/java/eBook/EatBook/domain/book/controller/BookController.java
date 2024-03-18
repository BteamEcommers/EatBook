package eBook.EatBook.domain.book.controller;


import eBook.EatBook.domain.book.form.BookForm;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.service.CategoryService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.review.entity.Review;
import eBook.EatBook.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor

@RequestMapping("/book")

public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final MemberService memberService;
    private final ReviewService reviewService;


    @GetMapping("/detail/{id}") //책에 대한 상세페이지
    public String bookDetail(Model model, @PathVariable("id") Integer id) {
        Book book = this.bookService.getBookById(id);
        List<Review> reviewList = this.reviewService.findAllByBook(book);
        model.addAttribute("book", book);
        model.addAttribute("reviewList", reviewList);
        return "book/book_detail";
    }
    @GetMapping("/create")
    public String bookCreate(Model model) {
        List<Category> categories = this.categoryService.getAllCategory();
        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("categories", categories);
        return "book/book_create_form";
    }
    @PostMapping("/create")
    public String bookCreate(@Valid BookForm bookForm, BindingResult bindingResult,
                             @RequestParam(value = "categoryName", defaultValue = "기본 카테고리") String categoryName,
                             RedirectAttributes redirectAttributes, @RequestParam(value = "thumbnail",required = false) MultipartFile thumbnail, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/book_create_form";
        }

        Category category = this.categoryService.getCategoryByCategoryName(categoryName);
        Member seller = this.memberService.findByUsername(principal.getName());
        try {
            Book book = bookService.createWithImage(bookForm, category, seller, thumbnail);
            redirectAttributes.addFlashAttribute("success", "도서가 성공적으로 등록되었습니다.");
            // 카테고리 페이지로 리다이렉트
            return "redirect:/book/list";


        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "도서 등록에 실패하였습니다.");
        }
        return "redirect:/book/list";
    }

    @GetMapping("/list")
    public String bookList(Model model, @RequestParam(value ="page", defaultValue = "0") int page, Principal principal){
        if (principal != null) {
            Member member = this.memberService.getMember(principal.getName());
            model.addAttribute("member", member);
        }
        Page<Book> paging = this.bookService.getList(page);
        List<Category> categoryList = this.categoryService.getAllCategory();
        model.addAttribute("paging", paging);
        model.addAttribute("categoryList", categoryList);

        return "/book/book_list";
    }
    @GetMapping("/list/{categoryName}")
    public String bookListCategory(Model model, @PathVariable("categoryName") String categoryName, @RequestParam(value ="page", defaultValue = "0") int page, Principal principal){
        if (principal != null) {
            Member member = this.memberService.getMember(principal.getName());
            model.addAttribute("member", member);
        }

        Category category = this.categoryService.getCategoryByCategoryName(categoryName);
        List<Category> categoryList = this.categoryService.getAllCategory();
        Page<Book> paging =  this.bookService.getListByCategory(category, page);
        model.addAttribute("paging", paging);
        model.addAttribute("categoryEntity", category);
        model.addAttribute("categoryList", categoryList);
        return "/book/book_list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list/seller")
    public String bookListSeller(Model model, Principal principal){

        Member member = this.memberService.findByUsername(principal.getName());
        // 최종본에 추가(seller가 아니면 리다이렉트)
//        if(!member.isSeller()){
//            return "redirect:/";
//        }
        List<Book> bookList = this.bookService.findAllBySeller(member);
        model.addAttribute("bookList", bookList);
        return "/book/sellerBookList";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer bookId, Principal principal){
        Book book = this.bookService.getBookById(bookId);
        Member member = this.memberService.findByUsername(principal.getName());
        if(book.getSeller().getId() != member.getId()){
            return "redirect:/";
        }
        this.bookService.deleteBook(book);
        return "redirect:/book/list/seller";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{bookId}")
    public String modifyBook(Model model, BookForm bookForm, @PathVariable("bookId") Integer bookId, Principal principal){
        List<Category> categories = this.categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        Book book = this.bookService.getBookById(bookId);
        Member member = this.memberService.findByUsername(principal.getName());
        if(book.getSeller().getId() != member.getId()){
            return "redirect:/";
        }
        model.addAttribute("book",book);

        bookForm.setSubject(book.getSubject());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setContent(book.getContent());
        bookForm.setPrice(book.getPrice());
        bookForm.setPublisher(book.getPublisher());
        bookForm.setBookIntroduce(book.getBookIntroduce());
        bookForm.setDiscount(book.getDiscount());
        return "book/book_modify_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{bookId}")
    public String modifyBook(@Valid BookForm bookForm, BindingResult bindingResult, Model model,
                             @PathVariable("bookId") Integer bookId, Principal principal, @RequestParam(value = "categoryName", defaultValue = "기본 카테고리") String categoryName,
                              @RequestParam(value = "thumbnail",required = false) MultipartFile thumbnail){
        if (bindingResult.hasErrors()) {
            return "/book_modify_form";
        }
        Book book = this.bookService.getBookById(bookId);
        Category category = this.categoryService.getCategoryByCategoryName(categoryName);

        this.bookService.modifyBook(book, bookForm, category, thumbnail);

        return "redirect:/book/list/seller";
    }



}



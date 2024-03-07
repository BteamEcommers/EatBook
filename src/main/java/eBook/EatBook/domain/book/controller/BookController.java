package eBook.EatBook.domain.book.controller;


import eBook.EatBook.domain.book.form.BookForm;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import eBook.EatBook.domain.category.service.CategoryService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.category.service.CategoryService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.review.entity.Review;
import eBook.EatBook.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequiredArgsConstructor

@RequestMapping("/book")

public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final MemberService memberService;
    private final ReviewService reviewService;

//    @GetMapping("/list")
//    public String handleCategoryListRequest( Model model) {
//        List<Category> categories = this.categoryService.getCategory();
//        List<Book> books = this.bookService.getList(id);
//        model.addAttribute("categories", categories);
//        model.addAttribute("books", books);
//        return "book/books";
//    }

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
                             @RequestParam("file") MultipartFile file,
                             @RequestParam(value = "categoryName", defaultValue = "기본 카테고리") String categoryName,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/book_create_form";
        }

        Category category = this.categoryService.getCategoryByCategoryName(categoryName);
        // 이미지 업로드 로직 추가
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Book book = bookService.createWithImage(
                    bookForm.getSubject(),
                    bookForm.getContent(),
                    bookForm.getBookIntroduce(),
                    bookForm.getAuthor(),
                    bookForm.getPrice(),
                    bookForm.getDiscount(),
                    bookForm.getPublisher(),file, category);
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
//    @GetMapping("/books/{category}")
//    public String getBooksByCategory(@PathVariable("category") String categoryName, Model model) {
//        Category category =  this.categoryService.getCategoryByCategoryName(categoryName);
//        List<Book> books = bookService.findBooksByCategory(category);
//
//
//        model.addAttribute("categoryName", categoryName);
//        model.addAttribute("books", books);
//        return "books/category_books";
//    }
}



package eBook.EatBook.domain.book.controller;


import eBook.EatBook.domain.book.form.BookForm;
import eBook.EatBook.domain.book.form.CategoryForm;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import eBook.EatBook.domain.category.service.CategoryService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String handleCategoryListRequest( Model model) {
        List<Category> category = this.categoryService.getCategory();
        List<Book> books = this.bookService.getList(id);
        model.addAttribute("category", category);
        model.addAttribute("books", books);
        return "book/books";
    }

    @GetMapping("/detail/{id}") //책에 대한 상세페이지
    public String bookDetail(Model model, @PathVariable("id") Integer id) {
        Book book = (Book) this.bookService.getList(id);
        model.addAttribute("book", book);
        return "book/book_detail";
    }
    @GetMapping("/create")
    public String bookCreate(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "book/book_create_form";
    }
    @PostMapping("/create")
    public String bookCreate(@Valid BookForm bookForm, BindingResult bindingResult,
                             @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/book_create_form";
        }
        // 이미지 업로드 로직 추가
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
//            String category = String.valueOf(bookForm.getCategory()); // 폼에서 선택된 카테고리 정보 가져오기

            Book book = bookService.createWithImage(bookForm.getSubject(), bookForm.getContent(),
                    bookForm.getBookIntroduce(),
                    bookForm.getAuthor(),
//                    (jdk.jfr.Category) bookForm.getCategory(),
                    bookForm.getPrice(),
                    bookForm.getDiscount(),
                    bookForm.getPublisher(),file);
            redirectAttributes.addFlashAttribute("success", "도서가 성공적으로 등록되었습니다.");
            // 카테고리 페이지로 리다이렉트
            return "redirect:/book/list";


        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "도서 등록에 실패하였습니다.");
        }
        return "redirect:/book/list";

    }
    @PostMapping("/category")
    public String reportComment(@PathVariable("id") Integer id, Principal principal,
                                @Valid CategoryForm categoryForm, BindingResult bindingResult)  {
        Book book = this.bookService.getBook(id);

        if (bindingResult.hasErrors()){
            return "article_detail";
        }

        Member member = this.memberService.getMember(principal.getName());
        String categoryType = determineReportType(categoryForm.getCategory());

        this.bookService.report(book,categoryForm.getCategory(),member,categoryType);
        return "redirect:/category/list";
    }

    private String determineReportType(String selectedValue) {

        if ("베스트셀러".equals(selectedValue)) {
            return "베스트셀러";
        } else if ("외국도서".equals(selectedValue)) {
            return "외국도서";
        } else if ("국내도서".equals(selectedValue)) {
            return "국내도서";
        } else if ("무료도서".equals(selectedValue)) {
            return "무료도서";
        } else if ("신간도서".equals(selectedValue)) {
            return "국내도서";
        }
        return "기타";
    }
}



package eBook.EatBook.domain.book.controller;


import eBook.EatBook.domain.book.form.BookForm;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.book.entity.Book;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor

@RequestMapping("/book")

public class BookController {

    private final BookService bookService;


    @GetMapping("/list")
    public String bookList(Model model){
        List<Book> bookList = this.bookService.getList();
        model.addAttribute("bookList", bookList);
        return "book/book_list";
    }

    @GetMapping("/detail/{id}") //책에 대한 상세페이지
    public String bookDetail(Model model, @PathVariable("id") Integer id) {
        Book book = this.bookService.getList(id);
        model.addAttribute("book", book);
        return "book/book_detail";
    }

    @GetMapping("/create")  //판매자가 책을 올림
    public String bookCreate(BookForm bookForm) {
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
            Book book = bookService.createWithImage(bookForm.getSubject(), bookForm.getContent(),
                    bookForm.getBookIntroduce(),
                    bookForm.getAuthor(),
                    (Category) bookForm.getCategory(),
                    bookForm.getPrice(),
                    bookForm.getDiscount(),
                    bookForm.getPublisher(),file);
            redirectAttributes.addFlashAttribute("success", "도서가 성공적으로 등록되었습니다.");
            // 도서가 등록된 후 리스트 페이지로 리다이렉트
            return "redirect:/category/list";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "도서 등록에 실패하였습니다.");
        }
        return "redirect:/category/list";
    }
}



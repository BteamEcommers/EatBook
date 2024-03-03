package eBook.EatBook.domain.book.controller;


import eBook.EatBook.domain.book.form.BookForm;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import eBook.EatBook.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
    @RequiredArgsConstructor
    @RequestMapping("/book")
    public class BookController {

        private final BookService bookService;
        private final BookRepository bookRepository;
        private final CategoryRepository categoryRepository;
        private final CategoryService categoryService;

        @GetMapping("/detail/{id}") //책에 대한 상세페이지
        public String bookDetail(Model model, @PathVariable("id") Integer id) {
            Book book = this.bookService.getList(id);
            model.addAttribute("book", book);
            return "book/book_detail";
        }
    @GetMapping("/create")
    public String bookCreate(Model model) {
        model.addAttribute("bookForm", new BookForm());
        List<Category> categories = categoryService.getAllCategories(); // 카테고리 목록 가져오기
        model.addAttribute("categories", categories); // 카테고리 목록 전달
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
                    bookForm.getCategory(),
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



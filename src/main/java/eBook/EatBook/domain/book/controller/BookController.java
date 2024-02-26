package eBook.EatBook.domain.book.controller;


import eBook.EatBook.domain.book.form.BookForm;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.book.entity.Book;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
//@Requestmapping("/book)-추가하기
public class BookController {

    private final BookService bookService;

    @GetMapping("/detail/{id}") //책에 대한 상세페이지
    public String bookDetail (Model model, @PathVariable("id")Integer id) {
        Book book = this.bookService.getList(id);
        model.addAttribute("book", book);
        return "book_detail";
    }
    @GetMapping("/create")  //판매자가 책을 올림
    public String bookCreate(BookForm bookForm){
        return "book_create_form";
    }
    @PostMapping("/create")
    public String bookCreate(@Valid BookForm bookForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "book_create_form";
        }
        this.bookService.create(bookForm.getSubject(), bookForm.getContent());
        return "redirect:/book/list";
    }
    }


package eBook.EatBook.book.controller;


import eBook.EatBook.book.service.BookService;
import eBook.EatBook.book.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")   //각 카테고리 리스트로
    public String bookList(Model model){
        List<Book> bookList = this.bookService.getList();
        model.addAttribute("bookList", bookList);
        return "book_list";
    }

    @GetMapping("/detail/{id}") //책에 대한 상세페이지
    public String bookDetail (Model model, @PathVariable("id")Integer id){
        Book book = this.bookService.getList(id);
        model.addAttribute("book",book);
        return "book_detail";
    }
//    @GetMapping("/create")  //도서 등록
//    public String bookCreate (){
//        return "book_create_form";
//    }
//    @PostMapping("/create")
//    public String bookCreate(){
//
//    }

}

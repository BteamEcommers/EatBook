package eBook.EatBook.domain.category.controller;


import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import eBook.EatBook.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {


    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final BookService bookService;
    private final BookRepository bookRepository;

    @GetMapping("/list")
    public String handleCategoryListRequest(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        // 도서 목록 조회 (예시)
        List<Book> books = bookRepository.findAll(); // 또는 다른 방식으로 도서 목록을 조회
        model.addAttribute("books", books); // 도서 목록을 모델에 추가
        return "book/books";
    }

    //이 밑으로는 일단 내두시면 될 것 같습니다. 혹시몰라서 남겨두려 합니다.(추후 삭제 예정입니다.)

    @GetMapping("/foreign1")
    public String foreign1() {
        // 책 카테고리 선택 페이지로 이동
        return "book/foreign_books1";
    }
    @GetMapping("/foreign2")
    public String foreign2() {
        // 책 카테고리 선택 페이지로 이동
        return "book/foreign_books2";
    }
    @GetMapping("/foreign3")
    public String foreign3() {
        // 책 카테고리 선택 페이지로 이동
        return "book/foreign_books3";
    }
    @GetMapping("/korea1")
    public String korea1() {
        // 책 카테고리 선택 페이지로 이동
        return "book/korea_books1";
    }
    @GetMapping("/korea2")
    public String korea2() {
        // 책 카테고리 선택 페이지로 이동
        return "book/korea_books2";
    }
    @GetMapping("/korea3")
    public String korea3() {
        // 책 카테고리 선택 페이지로 이동
        return "book/korea_books3";
    }
    @GetMapping("/best1")
    public String best1() {
        // 책 카테고리 선택 페이지로 이동
        return "book/best_books1";
    }
    @GetMapping("/best2")
    public String best2() {
        // 책 카테고리 선택 페이지로 이동
        return "book/best_books2";
    }
    @GetMapping("/best3")
    public String best3() {
        // 책 카테고리 선택 페이지로 이동
        return "book/best_books3";
    }
    @GetMapping("/new1")
    public String new1(){
        return "book/new_books1";
    }
    @GetMapping("/new2")
    public String new2(){
        return "book/new_books2";
    }
    @GetMapping("/new3")
    public String new3(){
        return "book/new_books3";
    }
    @GetMapping("/free1")
    public String free1(){
        return "book/free_books1";
    }
    @GetMapping("/free2")
    public String free2(){
        return "book/free_books2";
    }
    @GetMapping("/free3")
    public String free3(){
        return "book/free_books3";
    }


}




package eBook.EatBook.domain.category.controller;


import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.category.entity.Category;
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

    @GetMapping("/list")
    public String handleBooksRequest() {
       // 책 카테고리 선택 페이지로 이동
       return "books";
   }
@GetMapping("/foreign")
public String foreign() {
    // 책 카테고리 선택 페이지로 이동
    return "foreign_books";
}

//    @GetMapping("/books/category/{category}")
//    public String handleCategoryRequest(@PathVariable("category")String category ) {
//        // 각 카테고리에 해당하는 페이지로 이동
//        return category + "_books";
//    }


    @PostMapping("/category/foreign")
    public String foreign(Model model, @RequestParam(value="content") String content) {
        this.categoryService.foreign(content);
        // TODO: 답변을 저장한다.
        return "redirect:/category";
    }
}




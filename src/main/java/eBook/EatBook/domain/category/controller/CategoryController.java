package eBook.EatBook.domain.category.controller;


import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class CategoryController {

    private final BookService bookService;

    @GetMapping("/book")
    public String categoryBook(@RequestParam(value = "category", required=false) String category, Model model) {

        // category 값이 null이면 기본 카테고리를 설정
        if (category == null) {
            category = "베스트셀러"; // 기본 카테고리 설정
        }

        try {
            List<Book> bookList;
            if ("베스트셀러".equals(category)) {
                bookList = bookService.getBestSellerBooks();
            } else if ("국내도서".equals(category)) {
                bookList = bookService.getDomesticBooks();
            } else if ("외국도서".equals(category)) {
                bookList = bookService.getForeignBooks();
            } else if ("신간도서".equals(category)) {
                bookList = bookService.getNewBooks();
            } else if ("도서대여".equals(category)) {
                bookList = bookService.getRentalBooks();
            } else if ("무료도서".equals(category)) {
                bookList = bookService.getFreeBooks();
            } else {
                throw new IllegalArgumentException("Invalid category: " + category);
            }
            model.addAttribute("bookList", bookList);
            model.addAttribute("category", category); // 카테고리 추가
            return "category_books";
        } catch (Exception e) {
            // Handle the exception here
            // For example, you can redirect to the error page
            return "error"; // Assuming "error.html" is the name of the error page
        }
    }


}

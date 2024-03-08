package eBook.EatBook.domain.category.controller;


import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import eBook.EatBook.domain.category.service.CategoryService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/create")
    public String createCategory(Model model){
        model.addAttribute("category", new Category());
        return "category/category_create_form";
    }
    @PostMapping("/create")
    public String createCategory(@RequestParam(value = "categoryName")String categoryName){

        this.categoryService.categoryCreate(categoryName);
        return "redirect:/book/list";
    }
}
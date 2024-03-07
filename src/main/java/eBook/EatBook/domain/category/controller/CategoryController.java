package eBook.EatBook.domain.category.controller;


import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/create")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "book/category_create_form";
    }

    @PostMapping("/create")
    public String createCategory(@RequestParam(value = "categoryName")String categoryName){
        this.categoryService.categoryCreate(categoryName);
        return "redirect:/book/list";
    }
}





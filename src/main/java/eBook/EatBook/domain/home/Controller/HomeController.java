package eBook.EatBook.domain.home.Controller;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.service.CategoryService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model, Principal principal, @RequestParam(value ="page", defaultValue = "0") int page) {
        if (principal != null) {
            Member member = this.memberService.getMember(principal.getName());
            model.addAttribute("member", member);
        }
        // bestSeller
        Page<Book> bestPaging = this.bookService.indexBestSellerList(page);
        // freeBook
        Page<Book> freePaging = this.bookService.indexFreeBookList(page);
        model.addAttribute(bestPaging);
        model.addAttribute(freePaging);
        return "mainhome";
    }

    @GetMapping("/test")
    public String test() {

        return "test";
    }


}

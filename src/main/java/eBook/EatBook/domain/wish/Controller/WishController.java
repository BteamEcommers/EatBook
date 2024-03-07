package eBook.EatBook.domain.wish.Controller;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.wish.Entity.Wish;
import eBook.EatBook.domain.wish.Service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wish")
public class WishController {

    private final WishService wishService;
    private final MemberService memberService;
    private final BookService bookService;


    @GetMapping("/add/{id}")
    public String addWish(@PathVariable(value = "id") Integer id, Principal principal) {
        Member member = this.memberService.findByUsername(principal.getName());
        Book book = this.bookService.getBookById(id);

        // 중복 체크: 해당 이벤트에 대한 Wish가 이미 존재하는지 확인
        if (wishService.hasWish(member, book)) {
            // 이미 Wish가 있으면 중복으로 추가하지 않고 이벤트 상세 페이지로 리다이렉트
            return String.format("redirect:/book/detail/%d", id);
        }

        // 중복이 아니면 Wish를 추가하고 회원에게 연결
        Wish wish = this.wishService.addWish(member, book);
        this.memberService.addWish(member, wish);

        return String.format("redirect:/book/detail/%d", id);
    }

    @GetMapping("/list")
    public String wishList(Model model, Principal principal) {
        if(principal==null) {
            return "redirect:/member/login";
        }
        Member member = this.memberService.findByUsername(principal.getName());

        //이벤트 게시물로 임시 사용(변경필요)
        List<Book> bookList = this.wishService.findProductByWish(member.getWishList());
        model.addAttribute("bookList",bookList);
        return "/wish/wish_list";
    }


    @GetMapping("/delete/{id}")
    public String deleteWish(@PathVariable(value = "id") Integer id, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Book book = bookService.getBookById(id);

        wishService.deleteWishByMemberAndBook(member, book);

        return "redirect:/wish/list";
    }

}

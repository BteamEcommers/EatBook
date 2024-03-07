package eBook.EatBook.domain.cartitem.Controller;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.cartitem.Service.CartItemService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
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
@RequestMapping("/cartitem")
public class CartItemController {

    private final CartItemService cartItemService;
    private final MemberService memberService;
    private final BookService bookService;


    @GetMapping("/add/{id}")
    public String addCartItem(@PathVariable(value = "id") Integer id, Principal principal) {
        Member member = this.memberService.findByUsername(principal.getName());
        Book book = this.bookService.getBookById(id);

        // 중복 체크: 해당 상품이 카트에 이미 존재하는지 확인
        if (cartItemService.hasWish(member, book)) {
            // 이미 카드에 있으면 중복으로 추가하지 않고 이벤트 상세 페이지로 리다이렉트
            return String.format("redirect:/book/detail/%d", id);
        }

        CartItem cartItem = this.cartItemService.addCartItem(member, book);
        this.memberService.addCartItem(member, cartItem);

        return String.format("redirect:/book/detail/%d", id);
    }

    @GetMapping("/list")
    public String cartList(Model model, Principal principal) {
        if(principal==null) {
            return "redirect:/member/login";
        }
        Member member = this.memberService.findByUsername(principal.getName());

        //이벤트 게시물로 임시 사용(변경필요)
        List<Book> bookList = this.cartItemService.findProductByCart(member.getCartList());
        model.addAttribute("bookList",bookList);
        int cartItemCount = this.cartItemService.getCartItemCount(member.getCartList());
        model.addAttribute("cartItemCount", cartItemCount);
        return "/cartitem/cart_list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable(value = "id") Integer id, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Book book = bookService.getBookById(id);

        cartItemService.deleteCartItemByMemberAndBook(member, book);

        return "redirect:/cartitem/list";
    }

}

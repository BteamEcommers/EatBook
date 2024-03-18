package eBook.EatBook.domain.cartitem.Controller;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.cartitem.Service.CartItemService;
import eBook.EatBook.domain.coupon.Entity.Coupon;
import eBook.EatBook.domain.coupon.Service.GetCouponService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cartitem")
public class CartItemController {

    private final CartItemService cartItemService;
    private final MemberService memberService;
    private final BookService bookService;
    private final GetCouponService getCouponService;



    @GetMapping("/add/{id}")
    public String addCartItem(@PathVariable(value = "id") Integer id, Principal principal) {
        Member member = this.memberService.findByUsername(principal.getName());
        Book book = this.bookService.getBookById(id);

        // 중복 체크: 해당 상품이 카트에 이미 존재하는지 확인
        if (cartItemService.hasCartItem(member, book)) {
            // 이미 카드에 있으면 중복으로 추가하지 않고 이벤트 상세 페이지로 리다이렉트
            return String.format("redirect:/book/detail/%d", id);
        }

        CartItem cartItem = this.cartItemService.addCartItem(member, book);
        this.memberService.addCartItem(member, cartItem);

        return String.format("redirect:/book/detail/%d", id);
    }

    @GetMapping("/list")
    public String cartList(Model model, Principal principal) {
        if(principal == null) {
            return "redirect:/member/login";
        }
        Member member = this.memberService.findByUsername(principal.getName());


        List<Coupon> couponList = this.getCouponService.findaddCouponByCoupon(member.getGetCouponList());
        model.addAttribute("couponList", couponList);

        List<Book> bookList = this.cartItemService.findProductByCart(member.getCartList());
        model.addAttribute("bookList", bookList);

        // 카트에 담긴 총 상품 금액 계산
        int total_price = 0;
        for(Book book : bookList) {
            total_price += book.getPrice();
        }
        model.addAttribute("total_price", total_price);

        // 카트에 담긴 총 할인 금액 계산
        int total_discount = 0;
        for(Book book : bookList) {
            total_discount += book.getDiscountPrice();
        }
        model.addAttribute("total_discount", total_discount);

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


    @PostMapping("/deleteSelected")
    public String deleteSelectedCartItems(@RequestParam("selectedItems") List<Integer> selectedItems, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());

        for (Integer itemId : selectedItems) {
            Book book = bookService.getBookById(itemId);
            cartItemService.deleteCartItemByMemberAndBook(member, book);
        }

        return "redirect:/cartitem/list";
    }


}

package eBook.EatBook.domain.cartitem.Controller;

import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.cartitem.Service.CartItemService;
import eBook.EatBook.domain.event.Entity.Event;
import eBook.EatBook.domain.event.Service.EventService;
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

    //임시로 Event 게시물을 사용(변경 필요)
    private final EventService eventService;

    @GetMapping("/add/{id}")
    public String addCartItem(@PathVariable(value = "id") Integer id, Principal principal) {
        Member member = this.memberService.findByUsername(principal.getName());
        Event event = this.eventService.getEvent(id);

        CartItem cartItem = this.cartItemService.addCartItem(member, event);
        this.memberService.addCartItem(member, cartItem);

        return String.format("redirect:/event/detail/%d", id);
    }

    @GetMapping("/list")
    public String cartList(Model model, Principal principal) {
        if(principal==null) {
            return "redirect:/member/login";
        }
        Member member = this.memberService.findByUsername(principal.getName());

        //이벤트 게시물로 임시 사용(변경필요)
        List<Event> eventList = this.cartItemService.findProductByCart(member.getCartList());
        model.addAttribute("eventList",eventList);
        int cartItemCount = this.cartItemService.getCartItemCount(member.getCartList());
        model.addAttribute("cartItemCount", cartItemCount);
        return "/cartitem/cart_list";
    }

}

//package eBook.EatBook.domain.cart.Controller;
//
//import eBook.EatBook.domain.cart.Entity.Cart;
//import eBook.EatBook.domain.cart.Repository.CartRepository;
//import eBook.EatBook.domain.cart.Service.CartService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class CartController {
//
//    private final CartRepository cartRepository;
//
//    @GetMapping("/cart/list")
//    public String Cartlist(Model model) {
//        List<cartList> cartLists = this.cartRepository.findAll();
//        model.addAttribute("cartlist", cartLists);
//        return "cart_list";
//    }
//
//    public void deleteCart(Cart cart) {
//        this.cartRepository.delete(cart);
//    }
//
//}

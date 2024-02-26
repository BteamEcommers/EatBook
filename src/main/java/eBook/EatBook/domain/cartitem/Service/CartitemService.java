//package eBook.EatBook.domain.cart.Service;
//
//import eBook.EatBook.domain.cart.Entity.Cart;
//import eBook.EatBook.domain.cart.Repository.CartRepository;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CartService {
//
//    private final CartRepository cartRepository;
//
//    public String cartCreate(String member, String book, Integer total_price, Integer total_discount){
//        Cart cart = Cart.builder()
//                .member(member)
//                .book(book)
//                .total_price(total_price)
//                .total_discout(total_discount)
//                .build();
//
//        this.cartRepository.save(cart);
//    }
//
//
//}

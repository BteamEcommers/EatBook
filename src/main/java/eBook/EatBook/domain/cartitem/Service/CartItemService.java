package eBook.EatBook.domain.cartitem.Service;

import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.cartitem.Repository.CartItemRepository;
import eBook.EatBook.domain.event.Entity.Event;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.wish.Entity.Wish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItem addCartItem(Member member, Event event) {
        CartItem cartItem = CartItem.builder()
                .member(member)
                .event(event)
                .build();

        this.cartItemRepository.save(cartItem);

        return cartItem;
    }

    public boolean hasWish(Member member, Event event) {
        return this.cartItemRepository.existsByMemberAndEvent(member, event);
    }

    public List<Event> findProductByCart(List<CartItem> cartList) {
        List<Event> eventList = new ArrayList<>();
        for(int i = 0; i < cartList.size(); i++) {
            eventList.add(cartList.get(i).getEvent());
        }
        return eventList;
    }

    public CartItem getCartItem(Integer CartId) {
        Optional<CartItem> cartItem = this.cartItemRepository.findById(CartId);
        if (cartItem.isEmpty()) {
            return null;
        }
        return cartItem.get();
    }

    public int getCartItemCount(List<CartItem> cartList) {
        if (cartList == null) {
            return 0;
        }

        // 카트 아이템 리스트의 크기를 반환
        return cartList.size();
    }

    public void deleteCartItemByMemberAndEvent(Member member, Event event) {
        CartItem CartItemToDelete = cartItemRepository.findByMemberAndEvent(member, event);

        if (CartItemToDelete != null) {
            cartItemRepository.delete(CartItemToDelete);
        }
    }
}

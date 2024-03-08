package eBook.EatBook.domain.cartitem.Service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.cartitem.Repository.CartItemRepository;
import eBook.EatBook.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItem addCartItem(Member member, Book book) {
        CartItem cartItem = CartItem.builder()
                .member(member)
                .book(book)
                .build();

        this.cartItemRepository.save(cartItem);

        return cartItem;
    }

    public boolean hasCartItem(Member member, Book book) {
        return this.cartItemRepository.existsByMemberAndBook(member, book);
    }

    public List<Book> findProductByCart(List<CartItem> cartList) {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < cartList.size(); i++) {
            bookList.add(cartList.get(i).getBook());
        }
        return bookList;
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

    public void deleteCartItemByMemberAndBook(Member member, Book book) {
        CartItem CartItemToDelete = cartItemRepository.findByMemberAndBook(member, book);

        if (CartItemToDelete != null) {
            cartItemRepository.delete(CartItemToDelete);
        }
    }


}

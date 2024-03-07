package eBook.EatBook.domain.cartitem.Repository;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    boolean existsByMemberAndBook(Member member, Book book);

    CartItem findByMemberAndBook(Member member, Book book);
}
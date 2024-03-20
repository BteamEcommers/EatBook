package eBook.EatBook.domain.orders.repository;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.orders.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Optional<Orders> findByBuyer(Member member);

    Optional<Orders> findByBuyerAndBook(Member member, Book book);

    Optional<Orders> findByBuyerAndCartItem(Member member, CartItem cartItem);

    Optional<Orders> findByRandomStringOrderId(String randomStringOrderId);

    List<Orders> findAllByBuyerAndIsOrdered(Member buyer, boolean isOrdered);
}

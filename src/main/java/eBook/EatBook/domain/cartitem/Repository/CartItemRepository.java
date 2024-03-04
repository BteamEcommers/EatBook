//package eBook.EatBook.domain.cartitem.Repository;
//
//import eBook.EatBook.domain.cartitem.Entity.CartItem;
//
//import eBook.EatBook.domain.event.Entity.Event;
//import eBook.EatBook.domain.member.entity.Member;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
//    boolean existsByMemberAndEvent(Member member, Event event);
//}
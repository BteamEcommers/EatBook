package eBook.EatBook.domain.wish.Repository;

import eBook.EatBook.domain.event.Entity.Event;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.wish.Entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WishRepository extends JpaRepository<Wish, Integer> {
    boolean existsByMemberAndEvent(Member member, Event event);

    Wish findByMemberAndEvent(Member member, Event event);
}

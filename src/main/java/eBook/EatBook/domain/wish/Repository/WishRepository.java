package eBook.EatBook.domain.wish.Repository;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.wish.Entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WishRepository extends JpaRepository<Wish, Integer> {
    boolean existsByMemberAndBook(Member member, Book book);

    Wish findByMemberAndBook(Member member, Book book);
}

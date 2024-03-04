package eBook.EatBook.domain.wish.Repository;

import eBook.EatBook.domain.wish.Entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Integer> {
}

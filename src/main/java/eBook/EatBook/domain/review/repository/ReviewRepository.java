package eBook.EatBook.domain.review.repository;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.review.entity.Review;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findById(SingularAttribute<AbstractPersistable, Serializable> id);

    List<Review> findAllByBook(Book book);

}

package eBook.EatBook.domain.review.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.review.DTO.ReviewForm;
import eBook.EatBook.domain.review.entity.Review;
import eBook.EatBook.domain.review.repository.ReviewRepository;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public Review getReview(Integer id) {
        Optional<Review> review = this.reviewRepository.findById(id);
        if (review.isEmpty()) {
            return null;
        }
        return review.get();
    }

    public void create(Book books, String content, Member author) {
        Review review = Review.builder()
                .book(books)
                .content(content)
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .author(author)
                .build();
        reviewRepository.save(review);
    }
    public void modify(Book book, String content){

        Review reviewModify = Review.builder()
                .content(content)
                .build();
        reviewRepository.save(reviewModify);

    }

    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    public List<Review> findAllByBook(Book book) {

        return this.reviewRepository.findAllByBook(book);
    }
}

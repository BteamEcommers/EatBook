package eBook.EatBook.domain.category.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.review.entity.Review;
import eBook.EatBook.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public Review getReview(Integer id) {
        Optional<Review> review = this.reviewRepository.findById(id);
        if (review.isEmpty()) {
            return null;
        }
        return review.get();
    }

    public void create(Book book, String content, Member author) {
        Review review = Review.builder()
                .book(book)
                .content(content)
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

    public List<Category> getAllCategory() {
        return null;
    }

    public Category getCategoryByCategoryName(String categoryName) {
        return null;
    }
}

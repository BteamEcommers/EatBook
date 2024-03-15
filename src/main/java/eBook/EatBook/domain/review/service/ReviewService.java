package eBook.EatBook.domain.review.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.review.entity.Review;
import eBook.EatBook.domain.review.repository.ReviewRepository;
import eBook.EatBook.report.Repository.ReportRepository;
import eBook.EatBook.report.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final ReportRepository reportRepository;


    public Review getReview(Integer id) {
        Optional<Review> review = this.reviewRepository.findById(id);
        if (review.isEmpty()) {
            return null;
        }
        return review.get();
    }

    public void create(Book books, String content, Member author,float rating) {
        Review review = Review.builder()
                .book(books)
                .content(content)
                .rating(rating)
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .author(author)
                .build();
        reviewRepository.save(review);
    }
    public void modify(Review review, String content,float rating){
        // 기존의 리뷰 객체의 내용을 업데이트
        review.setContent(content);
        review.setRating(rating);
        review.setModifiedDate(LocalDateTime.now()); // 수정일 업데이트
        reviewRepository.save(review); // 업데이트된 리뷰 저장
    }

    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    public List<Review> findAllByBook(Book book) {

        return this.reviewRepository.findAllByBook(book);
    }
    public void report(Review review, String reportContent, Member member, String reportType) {

        Report report = Report.builder()
                .author(member)
                .review(review)
                .content(reportContent)
                .reportType(reportType)
                .build();

        this.reportRepository.save(report);
    }
}

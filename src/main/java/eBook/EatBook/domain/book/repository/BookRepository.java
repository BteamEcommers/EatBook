package eBook.EatBook.domain.book.repository;

import eBook.EatBook.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    // 카테고리 이름을 기반으로 책을 검색하는 메서드
    List<Book> findByCategoryName(String categoryName);

    // 국내 도서를 검색하는 메서드
    List<Book> findByBook(String country);




    Book findBySubject(String subject);

    Book findBySubjectAndContent(String subject, String content);

    List<Book> findBySubjectLike(String subject);
}

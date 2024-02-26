package eBook.EatBook.domain.book.repository;

import eBook.EatBook.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {



    Book findBySubject(String subject);

    Book findBySubjectAndContent(String subject, String content);

    List<Book> findBySubjectLike(String subject);
}
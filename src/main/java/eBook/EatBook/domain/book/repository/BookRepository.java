package eBook.EatBook.domain.book.repository;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {



    Book findBySubject(String subject);

    Book findBySubjectAndContent(String subject, String content);

    List<Book> findBySubjectLike(String subject);

//    List<Book> findBySeller(Member );

    Page<Book> findAll(Pageable pageable);
//    Page<Book> findAllByCategory(Pageable pageable);
    List<Book> findByCategory(Category category);

    Optional<Book> findById(Long id);

    List<Book> findByCategoryCategoryName(String categoryName);

    List<Book> findAllByCategoryName(Category category);
}

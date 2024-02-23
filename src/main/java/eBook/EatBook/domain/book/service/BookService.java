package eBook.EatBook.domain.book.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getList() {
        return this.bookRepository.findAll();
    }

    public Book getList(Integer id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isEmpty()) {
            return null;
        }
        return book.get();
    }

    public List<Book> getBestSellerBooks() {
        return this.bookRepository.findAll();
    }

    public List<Book> getDomesticBooks() {
        return this.bookRepository.findByBook("국내");
    }

    public List<Book> getForeignBooks() {
        return this.bookRepository.findAll();
    }
    public List<Book> getNewBooks() {
        return this.bookRepository.findAll();
    }
    public List<Book> getRentalBooks() {
        return this.bookRepository.findAll();
    }
    public List<Book> getFreeBooks() {
        return this.bookRepository.findAll();
    }





    public void create(String subject, String content) {
        Book book = Book.builder()
                .subject(subject)
                .content(content)

                .build();

        this.bookRepository.save(book);
    }
}

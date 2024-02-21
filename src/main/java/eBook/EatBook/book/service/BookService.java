package eBook.EatBook.book.service;

import eBook.EatBook.book.entity.Book;
import eBook.EatBook.book.repository.BookRepository;
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
    public Book getList(Integer id){
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isEmpty()){
            return null;
        }
        return book.get();
    }
}

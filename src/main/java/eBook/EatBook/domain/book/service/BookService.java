package eBook.EatBook.domain.book.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.entity.FileUploadUtil;
import eBook.EatBook.domain.book.form.BookForm;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.category.entity.Category;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Book createWithImage(String subject, String content,
                                String bookIntroduce, String author, Category category,
                                Integer price, Float discount, String publisher
            , MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        Book book = Book.builder()
                .subject(subject)
                .content(content)
                .bookIntroduce(bookIntroduce)
                .author(author)
                .category(category)
                .price(price)
                .discount(discount)
                .publisher(publisher)
                .bookThumbnailImg(fileName)
                .build();
        bookRepository.save(book);

        String uploadDir = "book-thumbnails/" + book.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, image);

        return book;
    }
    public List<Book> getBooksByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }

}

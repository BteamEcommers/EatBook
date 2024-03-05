package eBook.EatBook.domain.book.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.entity.FileUploadUtil;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import eBook.EatBook.domain.member.entity.Member;
import jakarta.persistence.metamodel.SingularAttribute;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public List<Book> getList(Integer id) {
        return this.bookRepository.findAll();
    }

    public Book getBook(Integer id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isEmpty()) {
            return null;
        }
        return book.get();
    }

    public Book createWithImage(String subject, String content,
                                String bookIntroduce, String author,
                                Integer price, Float discount, String publisher
            , MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());  //이미지파일 업로드하는 과정
        Book book = Book.builder()
                .subject(subject)
                .content(content)
                .bookIntroduce(bookIntroduce)
                .author(author)
//                .category((eBook.EatBook.domain.category.entity.Category) category)
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

    public void report(Book book, String category, Member member, String categoryType) {

        eBook.EatBook.domain.category.entity.Category category1 = eBook.EatBook.domain.category.entity.Category.builder()
                .author(member)
                .book((List<Book>) book)
                .categoryName(category)
                .categoryType(categoryType)
                .build();

        this.categoryRepository.save(category1);
    }


    public List<Book> getList(SingularAttribute<AbstractPersistable, Serializable> id) {
        return null;
    }
}

package eBook.EatBook.domain.book.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.entity.FileUploadUtil;
import eBook.EatBook.domain.book.form.BookForm;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.category.repository.CategoryRepository;
import eBook.EatBook.domain.member.entity.Member;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;



    public Page<Book> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.bookRepository.findAll(pageable);
    }
    public Page<Book> getListByCategory(Category category, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.bookRepository.findAllByCategory(category, pageable);
    }

    public Book getBookById(Integer id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isEmpty()) {
            return null;
        }
        return book.get();
    }


    public Book createWithImage(BookForm bookForm
            , MultipartFile image, Category category, Member seller) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());  //이미지파일 업로드하는 과정


        Book book = Book.builder()
                .subject(bookForm.getSubject())
                .content(bookForm.getContent())
                .bookIntroduce(bookForm.getBookIntroduce())
                .author(bookForm.getAuthor())
                .category(category)
                .price(bookForm.getPrice())
                .discount(bookForm.getDiscount())
                .publisher(bookForm.getPublisher())
                .bookThumbnailImg(fileName)
                .seller(seller)
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        bookRepository.save(book);

        String uploadDir = "book-thumbnails/" + book.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, image);

        return book;
    }



    public List<Book> findAllBySeller(Member seller) {
        List<Book> sellerBookList = this.bookRepository.findBySeller(seller);
        if(sellerBookList == null){
            return null;
        }
        return sellerBookList;
    }
}

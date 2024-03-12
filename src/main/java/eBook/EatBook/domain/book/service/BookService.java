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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;


    @Value("${custom.fileDirPath}")
    private String fileDirPath;


    public Page<Book> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.bookRepository.findAll(pageable);
    }

    public Page<Book> getListByCategory(Category category, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
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
            , Category category, Member seller, MultipartFile thumbnail) throws IOException {
        String thumbnailRelPath = "book/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(fileDirPath + "/" + thumbnailRelPath);

        try {
            thumbnail.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Book book;
        double percent = (double) (100 - bookForm.getDiscount()) / 100;
        Integer discountPrice = (int) (bookForm.getPrice() * percent);

        book = Book.builder()
                .subject(bookForm.getSubject())
                .content(bookForm.getContent())
                .bookIntroduce(bookForm.getBookIntroduce())
                .author(bookForm.getAuthor())
                .category(category)
                .price(bookForm.getPrice())
                .discount(bookForm.getDiscount())
                .discountPrice(discountPrice)
                .publisher(bookForm.getPublisher())
                .thumbnailImg(thumbnailRelPath)
                .seller(seller)
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();


        bookRepository.save(book);


        return book;
    }


    public List<Book> findAllBySeller(Member seller) {
        List<Book> sellerBookList = this.bookRepository.findBySeller(seller);
        if (sellerBookList == null) {
            return null;
        }
        return sellerBookList;
    }

    public Page<Book> indexBestSellerList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("sellCount"));
        Pageable pageable = PageRequest.of(page, 4, Sort.by(sorts));
        return this.bookRepository.findAll(pageable);
    }

    public Page<Book> indexFreeBookList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.asc("price"));
        Pageable pageable = PageRequest.of(page, 4, Sort.by(sorts));
        return this.bookRepository.findAll(pageable);
    }


}

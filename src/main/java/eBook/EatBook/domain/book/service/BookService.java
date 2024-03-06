package eBook.EatBook.domain.book.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.entity.FileUploadUtil;
import eBook.EatBook.domain.book.repository.BookRepository;
import eBook.EatBook.domain.member.entity.Member;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getList() {
        return this.bookRepository.findAll();
    }


    public Page<Book> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.bookRepository.findAll(pageable);
    }
//    public Page<Book> getListByCategory(Category category, int page) {
//        Pageable pageable = PageRequest.of(page, 10);
//        return this.bookRepository.findAllByCategory(category, pageable);
//    }

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
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());  //이미지파일 업로드하는 과정
        Book book = Book.builder()
                .subject(subject)
                .content(content)
                .bookIntroduce(bookIntroduce)
                .author(author)
                .category((eBook.EatBook.domain.category.entity.Category) category) //원래 이코드가 아니지만 집에서 push 안해서 이걸로 대체(추후 수정예정)
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



//    public List<Book> sellerBookList(Member seller){
//        List<Book> sellerBookList = this.bookRepository.findBySeller(seller);
//        if(sellerBookList == null){
//            return null;
//        }
//        return sellerBookList;
//    }
}

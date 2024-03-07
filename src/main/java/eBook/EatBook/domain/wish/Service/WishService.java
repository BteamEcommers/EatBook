package eBook.EatBook.domain.wish.Service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.event.Entity.Event;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.service_center.Entity.Faq;
import eBook.EatBook.domain.wish.Entity.Wish;
import eBook.EatBook.domain.wish.Repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    public Wish addWish(Member member, Book book) {
        Wish wish = Wish.builder()
                .member(member)
                .book(book)
                .build();

        this.wishRepository.save(wish);

        return wish;
    }
    public boolean hasWish(Member member, Book book) {
        return this.wishRepository.existsByMemberAndBook(member, book);
    }

    public List<Book> findProductByWish(List<Wish> wishList) {
        List<Book> bookList = new ArrayList<>();
        for(int i = 0; i < wishList.size(); i++) {
            bookList.add(wishList.get(i).getBook());
        }
        return bookList;
    }
    public List<Wish> findAllWish(){
        return this.wishRepository.findAll();
    }

    public Wish getWish(Integer wishId) {
        Optional<Wish> wish = this.wishRepository.findById(wishId);
        if (wish.isEmpty()) {
            return null;
        }
        return wish.get();
    }

    public void deleteWishByMemberAndBook(Member member, Book book) {
        Wish wishToDelete = wishRepository.findByMemberAndBook(member, book);

        if (wishToDelete != null) {
            wishRepository.delete(wishToDelete);
        }
    }


}

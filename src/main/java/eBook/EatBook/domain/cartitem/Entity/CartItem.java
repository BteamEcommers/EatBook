package eBook.EatBook.domain.cartitem.Entity;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 책 (상품)
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}

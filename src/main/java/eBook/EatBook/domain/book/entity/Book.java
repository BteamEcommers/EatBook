package eBook.EatBook.domain.book.entity;


import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.review.entity.Review;
import eBook.EatBook.domain.wish.Entity.Wish;
import eBook.EatBook.global.baseEntity.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Book extends BaseEntity {

    @ManyToOne
    private Member seller;
    @OneToMany
    private List<Review> reviewList;

    @ManyToOne
    private Category category;
    @Column
    private String author;
    @Column
    private String publisher;
    @Column
    private String bookIntroduce;
    @Column
    private String bookThumbnailImg;
    @Column
    private Integer price;
    @Column
    private float discount;
    @Column
    private float averageRating;
    @Column
    private Integer sellCount;
    @Column
    private char ISBN;
    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;


    @Column
    private String categoryName;


    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "book")
    private List<CartItem> cartItems;

    //장바구니 (Cartitem)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartList;

    // 찜 (Wish)

    @ManyToOne
    @JoinColumn(name = "wish_id")
    private Wish wish;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wishList;


}

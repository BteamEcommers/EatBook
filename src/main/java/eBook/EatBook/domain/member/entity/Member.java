package eBook.EatBook.domain.member.entity;

import eBook.EatBook.domain.book.entity.Book;
//import eBook.EatBook.domain.cartitem.Entity.Cart;
//import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.coupon.Entity.Coupon;
import eBook.EatBook.domain.coupon.Entity.GetCoupon;
import eBook.EatBook.domain.wish.Entity.Wish;
import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String nickname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private String memberThumbnailImg;

    @Column
    private boolean isSeller;


    //장바구니 상품 (Cartitem)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartList;

    // 찜 (Wish)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wishList;

    // 쿠폰 (Coupon)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> couponList;

    // addCoupon(유저가 가지는 쿠폰)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GetCoupon> getCouponList;


    // 판매자, admin이 필요한 계좌번호, 금융기관명, 예금주 Column
    // 계좌번호
    @Column
    private String accountNumber;

    // 은행이름
    @Column
    private String bankName;

    // 예금주
    @Column
    private String accountHolderName;


    // 구매자가 가진 책 리스트
    @OneToMany
    private List<Book> bookList;

    // 판매자가 가진 책 리스트
    @OneToMany
    private List<Book> bookSellList;
}

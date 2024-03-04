package eBook.EatBook.domain.member.entity;

//import eBook.EatBook.domain.cartitem.Entity.Cart;
//import eBook.EatBook.domain.cartitem.Entity.CartItem;
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

//    //장바구니
//    @OneToMany(mappedBy = "member")
//    private List<Cart> carts;

//    //장바구니 상품 (Cartitem)
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CartItem> cartList;

    // 찜 (Wish)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wishList;


    // 판매자가 가진 책 리스트
//    @OneToMany
//    private List<book> bookList;
}

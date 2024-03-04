package eBook.EatBook.domain.orders.entity;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.order_item.entity.OrderItem;
import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Orders extends BaseEntity {
    @Column
    private Integer bookPrice;

    @Column
    private String title;

    @Column
    private Integer totalPrice;

    @Column
    private Integer totalDiscount;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Member buyer;

    @OneToMany
    private List<OrderItem> orderItemList;

//    @ManyToOne
//    private CartItem cartItem;
}

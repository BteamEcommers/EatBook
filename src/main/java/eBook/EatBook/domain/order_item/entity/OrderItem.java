package eBook.EatBook.domain.order_item.entity;


import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.orders.entity.Orders;
import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class OrderItem extends BaseEntity {
    @Column
    private String subject;

    @Column
    private Integer bookPrice;

    @ManyToOne
    private Orders orders;

    @ManyToOne
    private Book book;

    @ManyToOne
    private CartItem cartItem;




}

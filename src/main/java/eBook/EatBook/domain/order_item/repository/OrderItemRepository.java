package eBook.EatBook.domain.order_item.repository;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.order_item.entity.OrderItem;
import eBook.EatBook.domain.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findAllByOrders(Orders orders);

}

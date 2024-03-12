package eBook.EatBook.domain.order_item.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.order_item.entity.OrderItem;
import eBook.EatBook.domain.order_item.repository.OrderItemRepository;
import eBook.EatBook.domain.orders.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem getOrderItemById(Integer orderItemId) {
        Optional<OrderItem> optionalOrderItem = this.orderItemRepository.findById(orderItemId);
        if(optionalOrderItem.isEmpty()){
            return null;
        }
        return optionalOrderItem.get();
    }

    //  단건 결제
    public OrderItem createOrderItem(Orders orders, Book book){
        OrderItem orderItem = OrderItem.builder()
                .orders(orders)
                .book(book)
                .subject(book.getSubject())
                .bookPrice(book.getPrice())
                .build();



        this.orderItemRepository.save(orderItem);

        return orderItem;
    }

    public List<OrderItem> findAllByOrders(Orders orders){
        List<OrderItem> orderItemList = this.orderItemRepository.findAllByOrders(orders);
        if(orderItemList.isEmpty()){
            return null;
        }
        return orderItemList;
    }
}

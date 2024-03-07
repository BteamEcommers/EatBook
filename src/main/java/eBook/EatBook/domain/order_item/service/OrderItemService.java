package eBook.EatBook.domain.order_item.service;

import eBook.EatBook.domain.order_item.entity.OrderItem;
import eBook.EatBook.domain.order_item.repository.OrderItemRepository;
import eBook.EatBook.domain.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

package eBook.EatBook.domain.order_item.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.cartitem.Repository.CartItemRepository;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.order_item.entity.OrderItem;
import eBook.EatBook.domain.order_item.repository.OrderItemRepository;
import eBook.EatBook.domain.orders.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;

    public OrderItem getOrderItemById(Integer orderItemId) {
        Optional<OrderItem> optionalOrderItem = this.orderItemRepository.findById(orderItemId);
        if(optionalOrderItem.isEmpty()){
            return null;
        }
        return optionalOrderItem.get();
    }

    //  단건 결제
    public ArrayList<OrderItem> createOrderItem(Orders orders, Book book){
        OrderItem orderItem = OrderItem.builder()
                .orders(orders)
                .book(book)
                .subject(book.getSubject())
                .bookPrice(book.getPrice())
                .build();

        ArrayList<OrderItem> ordersArrayList = new ArrayList<>();
        ordersArrayList.add(orderItem);


        this.orderItemRepository.save(orderItem);

        return ordersArrayList;
    }

    public List<OrderItem> findAllByOrders(Orders orders){
        List<OrderItem> orderItemList = this.orderItemRepository.findAllByOrders(orders);
        if(orderItemList.isEmpty()){
            return null;
        }
        return orderItemList;
    }
        // 수정중
    public ArrayList<OrderItem> createOrderItemByCartItem(Orders orders, Member member) {
        ArrayList<OrderItem> ordersArrayList = new ArrayList<>();
        List<CartItem> cartItemList = this.cartItemRepository.findAllByMember(member);
        if(cartItemList.isEmpty()){
            return null;
        }
        OrderItem orderItem = null;
        for(CartItem cartItem : cartItemList){
                     orderItem = OrderItem.builder()
                    .orders(orders)
                    .book(cartItem.getBook())
                    .subject(cartItem.getBook().getSubject())
                    .bookPrice(cartItem.getBook().getPrice())
                    .build();


            ordersArrayList.add(orderItem);
        }




        this.orderItemRepository.save(orderItem);

        return ordersArrayList;
    }
}

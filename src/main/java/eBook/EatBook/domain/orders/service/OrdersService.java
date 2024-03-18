package eBook.EatBook.domain.orders.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.order_item.entity.OrderItem;
import eBook.EatBook.domain.orders.entity.Orders;
import eBook.EatBook.domain.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

//    // 카트 상품 결제
//    public Orders addCartitems(CartItem cartItem, Member member) {
//        Orders orders =
//
//    }

    // 단건 결제
    public Orders createOrders(Book book, Member member) {
        // random string 생성
        String orderCode = this.RandomCode();

        Orders orders = Orders.builder()
                .subject(book.getSubject())
                .totalPrice(book.getPrice())
                .book(book)
                .buyer(member)
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .randomStringOrderId(orderCode)
                .build();

        this.ordersRepository.save(orders);
        return orders;
    }

    public Orders findByMember(Member member) {
        Optional<Orders> optionalOrders = this.ordersRepository.findByBuyer(member);
        if(optionalOrders.isEmpty()){
            return null;
        }

        return optionalOrders.get();
    }

    public Orders findById(Integer ordersId) {
        Optional<Orders> optionalOrders = this.ordersRepository.findById(ordersId);
        if(optionalOrders.isEmpty()){
            return null;
        }

        return optionalOrders.get();
    }

    public Orders findByMemberAndBook(Member member, Book book) {
        Optional<Orders> optionalOrders = this.ordersRepository.findByBuyerAndBook(member, book);
        if(optionalOrders.isEmpty()){
            return null;
        }
        return optionalOrders.get();
    }

    public void addOrderItemList(Orders orders, ArrayList<OrderItem> orderItemList) {
        Orders orders1 = orders.toBuilder()
                .orderItemList(orderItemList)
                .build();

        this.ordersRepository.save(orders1);
    }

    public void isOrdered(Orders orders){
        Orders orders1 = orders.toBuilder()
                .isOrdered(true)
                .isRebated(false)
                .build();

        this.ordersRepository.save(orders1);
    }

    public String RandomCode() {
        StringBuilder randomCode = new StringBuilder();
        // 대문자 A-Z 랜덤 알파벳 생성
        for (int i = 1; i <= 6; i++) {
            // (Math.random() * 26 => 0 ~ 25 까지의 랜덤한 실수
            //  "대문자 A의 10진수 아스키 코드 번호" == 65
            char ch = (char) ((Math.random() * 26) + 65);
            randomCode.append(ch);
        }

        return randomCode.toString();
    }

    public Orders findByRandomStringOrderId(String randomStringOrderId){
        Optional<Orders> optionalOrders = this.ordersRepository.findByRandomStringOrderId(randomStringOrderId);
        if(optionalOrders.isEmpty()){
            return null;
        }

        return optionalOrders.get();
    }

}

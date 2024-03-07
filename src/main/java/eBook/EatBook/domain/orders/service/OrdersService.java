package eBook.EatBook.domain.orders.service;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.orders.entity.Orders;
import eBook.EatBook.domain.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    // 단건 결제
    public Orders createOrders(Book book, Member member) {
        Orders orders = Orders.builder()
                .subject(book.getSubject())
                .totalPrice(book.getPrice())
                .book(book)
                .buyer(member)
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
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
}

package eBook.EatBook.domain.orders.controller;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.order_item.entity.OrderItem;
import eBook.EatBook.domain.order_item.service.OrderItemService;
import eBook.EatBook.domain.orders.entity.Orders;
import eBook.EatBook.domain.orders.service.OrdersService;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final OrderItemService orderItemService;
    private final MemberService memberService;
    private final BookService bookService;

    // 단건 결제
    @GetMapping("/pay/progress/{orderId}")
    public String ordersPay(Model model, @PathVariable("orderId") Integer orderId, Principal principal){
        Orders orders = this.ordersService.findById(orderId);
        model.addAttribute("orders", orders);
        return "/orders/ordersProgress";
    }

    @GetMapping("/pay/{bookId}")
    public String ordersPayOne(Model model, @PathVariable("bookId") Integer bookId, Principal principal){
        Book book = this.bookService.getBookById(bookId);
        Member member = this.memberService.findByUsername(principal.getName());
        Orders orders =  this.ordersService.findByMemberAndBook(member, book);
        model.addAttribute("orders", orders);
        return "/orders/ordersPay";
    }

    @PostMapping("/pay/{bookId}")
    public String ordersPayPost(Model model, @PathVariable("bookId") Integer bookId, Principal principal){
        Book book = this.bookService.getBookById(bookId);
        Member member = this.memberService.findByUsername(principal.getName());
        Orders orders = this.ordersService.createOrders(book, member);
        model.addAttribute("orders", orders);
        return  String.format("redirect:/pay/progress/%d", orders.getId());
    }
    @GetMapping("/confirm")
    public String ordersConfirm(){
        
        return "/orders/ordersConfirm";
    }
}

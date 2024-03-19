package eBook.EatBook.domain.orders.controller;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.book.service.BookService;
import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.cartitem.Service.CartItemService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final OrderItemService orderItemService;
    private final MemberService memberService;
    private final BookService bookService;
    private final CartItemService cartItemService;



    // 단건 결제
    @GetMapping("/pay/progress/{orderId}")
    public String ordersPay(Model model, @PathVariable("orderId") Integer orderId, Principal principal){
        Orders orders = this.ordersService.findById(orderId);
        List<OrderItem> orderItemList = this.orderItemService.findAllByOrders(orders);
        model.addAttribute("orders", orders);
        model.addAttribute("orderItemList", orderItemList);
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
        if(member.getBookList().contains(book)){
            return "/orders/orders_check";
        }

        Orders orders = this.ordersService.createOrders(book, member);
        ArrayList<OrderItem> orderItemList = this.orderItemService.createOrderItem(orders, book);
        this.ordersService.addOrderItemList(orders, orderItemList);

        return  String.format("redirect:/orders/pay/progress/%d", orders.getId());
    }

    @PostMapping("/pay/cartItem/{memberId}")
    public String orderCartItem(Model model, @PathVariable("memberId") Integer memberId, Principal principal){

        Member member = this.memberService.findByUsername(principal.getName());
        Orders orders = this.ordersService.createOrders(null, member);
        ArrayList<OrderItem> orderItemList = this.orderItemService.createOrderItemByCartItem(orders, member);
        this.ordersService.addOrderItemList(orders, orderItemList);

        return String.format("redirect:/orders/pay/progress/%d", orders.getId());
    }

    @GetMapping("/confirm")
    public String ordersConfirm(){
        
        return "/orders/ordersConfirm";
    }

    @GetMapping("/complete")
    public String ordersComplete(){

        return "/orders/ordersComplete";
    }

    @GetMapping("/check")
    public String ordersCheck(){

        return "/orders/orders_check";
    }
}

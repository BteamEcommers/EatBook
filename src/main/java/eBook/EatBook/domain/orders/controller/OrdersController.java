package eBook.EatBook.domain.orders.controller;

import eBook.EatBook.domain.orders.service.OrdersService;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping("/pay")
    public String ordersPay(){


        return "/orders/ordersPay";
    }
    @GetMapping("/confirm")
    public String ordersConfirm(){
        
        return "/orders/ordersConfirm";
    }
}

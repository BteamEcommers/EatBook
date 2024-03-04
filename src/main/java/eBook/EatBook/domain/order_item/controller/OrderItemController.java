package eBook.EatBook.domain.order_item.controller;

import eBook.EatBook.domain.order_item.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
}

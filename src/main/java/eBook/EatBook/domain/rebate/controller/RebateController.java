package eBook.EatBook.domain.rebate.controller;

import eBook.EatBook.domain.rebate.service.RebateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rebate")
public class RebateController {
    private final RebateService rebateService;

    @GetMapping("/admin")
    public String adminRebateList(){

        return "rebate/rebate_admin";
    }

    @GetMapping("/seller")
    public String sellerRebateList(){

        return "rebate/rebate_seller";
    }
}

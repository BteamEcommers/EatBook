package eBook.EatBook.domain.rebate.controller;

import eBook.EatBook.domain.rebate.service.RebateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rebate")
public class RebateController {
    private final RebateService rebateService;
}

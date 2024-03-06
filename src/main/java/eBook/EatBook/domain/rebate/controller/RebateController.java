package eBook.EatBook.domain.rebate.controller;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.rebate.entity.Rebate;
import eBook.EatBook.domain.rebate.service.RebateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rebate")
public class RebateController {
    private final RebateService rebateService;
    private final MemberService memberService;

//    @Controller("/create")
//    public String rebateCreate(@Valid RebateForm rebateForm, BindingResult bindingResult){
//
//        return
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/list")
    public String adminRebateList(Model model){
        List<Member> sellerList = this.memberService.findByIsSeller();
        model.addAttribute("sellerList", sellerList);
//        Rebate rebate = this.rebateService.getRebate();
//        model.addAttribute("rebate",rebate);
        return "/rebate/rebate_admin";
    }

    @GetMapping("/seller/list")
    public String sellerRebateList(Model model, Principal principal){
        // 현재 로그인한 판매자 Member
        Member member = this.memberService.findByUsername(principal.getName());
        model.addAttribute("bookList", member.getBookList());
//        Rebate rebate = this.rebateService.getRebate();
//        model.addAttribute("rebate",rebate);
        Integer totalPrice = 0;
        Integer totalSellCount = 0;
        double totalRebate;

        for(Book book : member.getBookList()){
            totalPrice += book.getPrice()* book.getSellCount();
            totalSellCount += book.getSellCount();
        }
        totalRebate = (totalPrice * 0.97);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalSellCount", totalSellCount);
        model.addAttribute("totalRebate", totalRebate);


        return "/rebate/rebate_seller";
    }
}

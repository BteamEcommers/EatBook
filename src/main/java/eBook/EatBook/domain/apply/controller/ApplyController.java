package eBook.EatBook.domain.apply.controller;

import eBook.EatBook.domain.apply.DTO.ApplySellerForm;
import eBook.EatBook.domain.apply.service.ApplyService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.role.UserRole;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.global.email.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {
    private final ApplyService applyService;
    private final MemberService memberService;
    private final EmailService emailService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/seller")
    public String applySeller(ApplySellerForm applySellerForm){
        return "/apply/applySellerForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/seller")
    public String applySeller(@Valid ApplySellerForm applySellerForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "/apply/applySellerForm";
        }
        Member member =  this.memberService.findByUsername(principal.getName());
        this.applyService.apply(applySellerForm, member);

        return "redirect:/apply/guide";
    }

    // 안내 페이지
    @GetMapping("/guide")
    public String applyGuide(){

        return "/apply/applyGuide";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public String applyList(){


        return "/apply/applyList";
    }


}

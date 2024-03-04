package eBook.EatBook.domain.apply.controller;

import eBook.EatBook.domain.apply.DTO.ApplySellerForm;
import eBook.EatBook.domain.apply.entity.Apply;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {
    private final ApplyService applyService;
    private final MemberService memberService;
    private final EmailService emailService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/seller")
    public String applySeller(ApplySellerForm applySellerForm) {
        return "/apply/applySellerForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/seller")
    public String applySeller(@Valid ApplySellerForm applySellerForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/apply/applySellerForm";
        }
        Member member = this.memberService.findByUsername(principal.getName());
        this.applyService.apply(applySellerForm, member);

        return "redirect:/apply/guide";
    }

    // 안내 페이지
    @GetMapping("/guide")
    public String applyGuide() {

        return "/apply/applyGuide";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public String applyList(Model model) {

        List<Apply> applyList = this.applyService.getApplyList();
        model.addAttribute("applyList", applyList);

        return "/apply/applyList";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approve/{applyId}")
    public String applyApprove(@PathVariable("applyId")Integer applyId){
        Apply apply = this.applyService.getApply(applyId);
        this.applyService.approve(apply);
        //send email
        this.emailService.send(apply.getApplicant().getEmail(),"[EatBook] 판매자 신청이 승인되었습니다.", "EatBook 판매자 신청이 승인 되었습니다. \n 이제 ebook 등록을 할 수 있습니다.");
        //member.isSeller = true
        this.memberService.approveSeller(apply.getApplicant(), apply);

        return "redirect:/apply/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/refuse/{applyId}")
    public String applyRefuse(@PathVariable("applyId")Integer applyId){
        Apply apply = this.applyService.getApply(applyId);
        Member member = this.memberService.findById(apply.getApplicant().getId());
        this.applyService.delete(applyId);
        //send refuse email
        this.emailService.send(member.getEmail(),"[EatBook] 판매자 신청이 거절되었습니다.", "EatBook 판매자 신청이 거절 되었습니다.");
        return "redirect:/apply/list";
    }



}

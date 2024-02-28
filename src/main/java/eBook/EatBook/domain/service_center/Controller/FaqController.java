package eBook.EatBook.domain.service_center.Controller;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.service_center.DTO.FaqCreateForm;
import eBook.EatBook.domain.service_center.Entity.Faq;
import eBook.EatBook.domain.service_center.Service.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

    private final FaqService faqService;
    private  final MemberService memberService;


    @GetMapping("/list")
    public String listFaq(Model model) {
        List<Faq> faqList = this.faqService.findAllFaq();
        model.addAttribute("faqList", faqList);
        return "/faq/faq_list";
    }

    @GetMapping("/create")
    public String createFaq(FaqCreateForm faqCreateForm) {
        return "/faq/faq_form";
    }

    @PostMapping("/create")
    public String createFaq(@Valid FaqCreateForm faqCreateForm, BindingResult bindingResult, Principal principal) {
        Member member = this.memberService.getMember(principal.getName());
        if(bindingResult.hasErrors()) {
            return "/faq/faq_form";
        }
        this.faqService.create(faqCreateForm, member);
        return "redirect:/faq/list";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modifyFaq(@PathVariable("id") Integer id, FaqCreateForm faqCreateForm, Principal principal, Model model) {
        Faq faq = this.faqService.getFaq(id);
        model.addAttribute("faq", faq);
        if (!faq.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        faqCreateForm.setTitle(faq.getTitle());
        faqCreateForm.setContent(faq.getContent());
        model.addAttribute("faq",faq);
        return "/faq/faq_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyFaq(@PathVariable("id") Integer id, Model model, Principal principal,
                                @Valid FaqCreateForm faqCreateForm, BindingResult bindingResult) {
        Member member = this.memberService.getMember(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("member", member);
            return "/faq/faq_form";
        }
        Faq faq = this.faqService.getFaq(id);
        if (!faq.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.faqService.modify(faq, member, faqCreateForm.getTitle(), faqCreateForm.getContent());
        return String.format("redirect:/faq/%s", id);
    }

    @GetMapping("/delete/{id}")
    public String deleteFaq(@PathVariable("id") Integer id, Principal principal) {
        Faq faq = this.faqService.getFaq(id);
        this.faqService.delete(faq);
        return "redirect:/faq/list";
    }


    @GetMapping("/detail/{id}")
    public String detailFaq(Model model, @PathVariable(value = "id") Integer id) {
        Faq faq = this.faqService.getFaq(id);
        model.addAttribute("faq",faq);
        return "/faq/faq_detail";
    }

}

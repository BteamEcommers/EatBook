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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String createFaq(FaqCreateForm faqCreateForm) {
        return "/faq/faq_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createFaq(@Valid FaqCreateForm faqCreateForm, BindingResult bindingResult, Principal principal) {
        Member member = this.memberService.getMember(principal.getName());
        if(bindingResult.hasErrors()) {
            return "/faq/faq_form";
        }
        this.faqService.create(faqCreateForm, member);
        return "redirect:/faq/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/modify/{id}")
    public String modifyFaq(@PathVariable("id") Integer id, FaqCreateForm faqCreateForm, Model model) {
        Faq faq = this.faqService.getFaq(id);
        model.addAttribute("faq", faq);
        faqCreateForm.setTitle(faq.getTitle());
        faqCreateForm.setContent(faq.getContent());
        return "/faq/faq_modifyForm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/modify/{id}")
    public String modifyFaq(@PathVariable("id") Integer id, Model model,
                            @Valid FaqCreateForm faqCreateForm, BindingResult bindingResult,
                            Principal principal) {
        Member member = this.memberService.getMember(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("member", member);
            return "/faq/faq_modifyForm";
        }
        Faq faq = this.faqService.getFaq(id);
        this.faqService.modify(faq, member, faqCreateForm.getTitle(), faqCreateForm.getContent());
        return "redirect:/faq/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteFaq(@PathVariable("id") Integer id, Principal principal) {
        Faq faq = this.faqService.getFaq(id);
        this.faqService.delete(faq);
        return "redirect:/faq/list";
    }

}

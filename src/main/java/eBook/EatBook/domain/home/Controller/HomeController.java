package eBook.EatBook.domain.home.Controller;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            Member member = this.memberService.getMember(principal.getName());
            model.addAttribute("member", member);
        }

        return "mainhome";
    }

    @GetMapping("/test")
    public String test() {

        return "test";
    }


}

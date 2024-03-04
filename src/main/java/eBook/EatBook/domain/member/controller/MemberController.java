package eBook.EatBook.domain.member.controller;

import eBook.EatBook.domain.event.Entity.Event;
import eBook.EatBook.domain.member.DTO.ConfirmCodeForm;
import eBook.EatBook.domain.member.DTO.FindPasswordForm;
import eBook.EatBook.domain.member.DTO.FindUsernameForm;
import eBook.EatBook.domain.member.DTO.MemberModifyForm;
import eBook.EatBook.domain.member.DTO.MemberRegisterForm;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.global.email.entity.Email1;
import eBook.EatBook.global.email.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final EmailService emailService;

    @GetMapping("/modify")
    public String modifyMember(MemberModifyForm memberModifyForm, Model model, Principal principal) {
        Member member = this.memberService.findByUsername(principal.getName());
        model.addAttribute("member", member);
        return "/member/modifyMemberForm";
    }

    @PostMapping("/modify/{id}")
    public String modifyMember(Model model, @Valid MemberModifyForm memberModifyForm, BindingResult bindingResult,
                               @PathVariable(value = "id") Integer id) throws IOException {
        Member modifyMember = this.memberService.findById(id);
        model.addAttribute("member", modifyMember);

        this.memberService.PasswordValidator(memberModifyForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/member/modifyMemberForm";
        }

        try {
            this.memberService.modify(memberModifyForm, modifyMember);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("modifyFailed ", "이미 등록된 사용자입니다.");
        } catch (Exception e) {
            bindingResult.reject("modifyFailed ", e.getMessage());
        }

        return "redirect:/member/modify";
    }


    @GetMapping("/register")
    public String register(MemberRegisterForm memberRegisterForm) {

        return "/member/registerForm";
    }

    @PostMapping("/register")
    public String register(@Valid MemberRegisterForm memberRegisterForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/member/registerForm";
        }
        if (!memberRegisterForm.getPassword1().equals(memberRegisterForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "/member/registerForm";
        }
        try {
            this.memberService.register(memberRegisterForm);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "/member/registerForm";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "/member/registerForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        // 현재 페이지의 URL을 세션에 저장
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("referrer", referrer);
        System.out.println("Referrer URL: " + referrer); // 디버깅용 로그
        return "/member/loginForm";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        // 로그인 성공 후 세션에서 이전 페이지의 URL을 읽어와 리다이렉트
        String referrer = (String) request.getSession().getAttribute("referrer");
        System.out.println("Redirecting to: " + referrer); // 디버깅용 로그
        if (referrer != null && !referrer.isEmpty()) {
            request.getSession().removeAttribute("referrer"); // 리다이렉트 후 세션에서 제거
            return "redirect:" + referrer;
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/";
    }

    @GetMapping("/findUsername")
    public String findUsername(FindUsernameForm findUsernameForm) {

        return "/member/findUsernameForm";
    }

    @GetMapping("/findPassword")
    public String findPassword(FindPasswordForm findPasswordForm) {

        return "/member/findPasswordForm";
    }

    @PostMapping("/sendConfirmCode")
    public String sendConfirmCode(@Valid FindUsernameForm findUsernameForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/member/findUsernameForm";
        }
        // 이메일 맞는지 확인
        Member member = this.memberService.findByEmail(findUsernameForm.getToEmail());
        if (member == null) {
            bindingResult.rejectValue("toEmail", "emailInCorrect",
                    "이메일을 다시 입력해주세요.");
            return "/member/findUsernameForm";
        }
        String confirmCode = this.RandomCode();
        this.emailService.saveConfirmCode(member, confirmCode);
        this.emailService.send(findUsernameForm.getToEmail(), "[EatBook]아이디 찾기를 위한 코드입니다.", String.format("코드 입력 \n [%s]", confirmCode));
        return "redirect:/member/confirmCodeUsername";
    }

    @GetMapping("/confirmCodeUsername")
    public String confirmCodeUsername(ConfirmCodeForm confirmCodeForm) {


        return "/member/confirmCodeUsernameForm";
    }

    @PostMapping("/confirmCodeUsername")
    public String confirmCodeUsername(@Valid ConfirmCodeForm confirmCodeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/member/confirmCodeUsernameForm";
        }
        Email1 email1 = this.emailService.findConfirmCode(confirmCodeForm.getConfirmCode());
        if (email1 == null) {
            bindingResult.rejectValue("confirmCode", "confirmCodeInCorrect",
                    "코드를 다시 입력해주세요.");
            return "/member/confirmCodeUsernameForm";
        }
        Member member = email1.getToMember();
        this.emailService.send(member.getEmail(), "[EatBook] 아이디를 확인하세요", String.format("\n 아이디 : [%s]", member.getUsername()));
        // 확인을 위한 이메일 객체 삭제
        this.emailService.delete(email1);
        return "redirect:/";
    }

    @PostMapping("/sendPasswordConfirmCode")
    public String sendPasswordConfirmCode(@Valid FindPasswordForm findPasswordForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/member/findPasswordForm";
        }

        // 아이디로 가져온 멤버와 이메일로 가져온 멤버가 일치하는지 확인
        Member memberByUsername = this.memberService.findByUsername(findPasswordForm.getUsername());
        Member memberByEmail = this.memberService.findByEmail(findPasswordForm.getToEmail());

        if (memberByUsername != memberByEmail) {
            bindingResult.rejectValue("toEmail", "toEmailInCorrect",
                    "아이디와 이메일이 일치하지 않습니다. 다시 입력해주세요.");
            return "/member/findPasswordForm";
        }
        String confirmCode = this.RandomCode();
        this.emailService.saveConfirmCode(memberByUsername, confirmCode);
        this.emailService.send(findPasswordForm.getToEmail(), "[EatBook]비밀번호 찾기를 위한 코드입니다.", String.format("코드 입력 \n [%s]", confirmCode));

        return "redirect:/member/confirmCodePassword";
    }

    @GetMapping("/confirmCodePassword")
    public String confirmCodePassword(ConfirmCodeForm confirmCodeForm) {

        return "/member/confirmCodePasswordForm";
    }

    @PostMapping("/confirmCodePassword")
    public String confirmCodePassword(@Valid ConfirmCodeForm confirmCodeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/member/confirmCodePasswordForm";
        }
        Email1 email1 = this.emailService.findConfirmCode(confirmCodeForm.getConfirmCode());
        if (email1 == null) {
            return "/member/findPasswordForm";
        }
        Member member = email1.getToMember();
        // 임시 비밀번호 발급
        String tempPassword = this.RandomCode();
        // 임시 비밀번호로 바꾸기
        this.memberService.changePassword(member, tempPassword);
        // 임시 비밀번호 이메일 발송
        this.emailService.send(member.getEmail(), "[EatBook] 임시 비밀번호를 확인하세요", String.format("\n 임시 비밀번호 : [%s]", tempPassword));
        // 확인을 위한 이메일 객체 삭제
        this.emailService.delete(email1);

        return "redirect:/";
    }

    // 인증 위한 랜덤 코드 생성기 (대문자 알파벳 6자리)
    public String RandomCode() {
        StringBuilder randomCode = new StringBuilder();
        // 대문자 A-Z 랜덤 알파벳 생성
        for (int i = 1; i <= 6; i++) {
            // (Math.random() * 26 => 0 ~ 25 까지의 랜덤한 실수
            //  "대문자 A의 10진수 아스키 코드 번호" == 65
            char ch = (char) ((Math.random() * 26) + 65);
            randomCode.append(ch);
        }

        return randomCode.toString();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profileMember(Model model, Principal principal) {
        Member member = this.memberService.findByUsername(principal.getName());
        model.addAttribute("member", member);
        return "/member/member_Profile";
    }

}

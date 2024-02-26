package eBook.EatBook.domain.member.service;

import eBook.EatBook.domain.member.DTO.MemberForm;
import eBook.EatBook.domain.member.DTO.MemberModifyForm;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(MemberForm memberForm) {
        Member member = Member.builder()
                .username(memberForm.getUsername())
                .nickname(memberForm.getNickname())
                .email(memberForm.getEmail())
                .password(passwordEncoder.encode(memberForm.getPassword1()))
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .role("customer")
                .build();

        this.memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        Optional<Member> optionalMember = this.memberRepository.findMemberByEmail(email);
        if (optionalMember.isEmpty()) {
            throw new RuntimeException();
        }

        return optionalMember.get();
    }

    public void modify(MemberModifyForm memberModifyForm, Member modifyMember) throws IOException {

        Member member = modifyMember.toBuilder()
                .nickname(memberModifyForm.getNickname())
                .password(passwordEncoder.encode(memberModifyForm.getPassword2()))
                .email(memberModifyForm.getEmail())
                .build();

        this.memberRepository.save(member);
    }

    public Member findById(Integer id) {
        Optional<Member> member = this.memberRepository.findById(id);
        if(member.isEmpty()) {
            throw new RuntimeException("존재하지 않는 사용자입니다.");
        }
        return member.get();
    }

    public Member findByUsername(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if(member.isEmpty()) {
            throw new RuntimeException("존재하지 않는 사용자입니다.");
        }
        return member.get();
    }

    // 패스워드 검증
    public BindingResult PasswordValidator(MemberModifyForm memberModifyForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return bindingResult;
        }
        if(!memberModifyForm.getPassword1().equals(memberModifyForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "패스워드가 일치하지 않습니다.");
            return bindingResult;
        }
        return bindingResult;
    }


}
package eBook.EatBook.domain.member.service;

import eBook.EatBook.domain.member.DTO.ChangePasswordForm;
import eBook.EatBook.domain.member.DTO.MemberForm;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public void changePassword(Member member, String newPassword){
        Member member1 = member.toBuilder()
                .password(passwordEncoder.encode(newPassword))
                .build();

        this.memberRepository.save(member1);
    }



    public Member findByEmail(String email) {
        Optional<Member> optionalMember = this.memberRepository.findMemberByEmail(email);
        if(optionalMember.isEmpty()){
            throw new RuntimeException();
        }

        return optionalMember.get();
    }
    public Member findByUsername(String username){
        Optional<Member> optionalMember = this.memberRepository.findByUsername(username);
        if(optionalMember.isEmpty()){
            throw new RuntimeException();
        }

        return optionalMember.get();
    }
}

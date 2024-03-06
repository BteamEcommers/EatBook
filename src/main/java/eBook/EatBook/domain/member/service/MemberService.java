package eBook.EatBook.domain.member.service;

import eBook.EatBook.domain.apply.entity.Apply;

import eBook.EatBook.domain.category.entity.Category;

//import eBook.EatBook.domain.cartitem.Entity.CartItem;
import eBook.EatBook.domain.cartitem.Entity.CartItem;

import eBook.EatBook.domain.member.DTO.MemberRegisterForm;
import eBook.EatBook.domain.member.DTO.MemberModifyForm;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.repository.MemberRepository;
import eBook.EatBook.domain.wish.Entity.Wish;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public void register(MemberRegisterForm memberRegisterForm) {
        Member member = Member.builder()
                .username(memberRegisterForm.getUsername())
                .nickname(memberRegisterForm.getNickname())
                .email(memberRegisterForm.getEmail())
                .password(passwordEncoder.encode(memberRegisterForm.getPassword1()))
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
        if (optionalMember.isEmpty()) {
            return null;
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

    public Member getMember(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isEmpty()) {
            return null;
        }
        return member.get();
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


    // 패스워드 검증
    public BindingResult PasswordValidator(MemberModifyForm memberModifyForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult;
        }
        if (!memberModifyForm.getPassword1().equals(memberModifyForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "패스워드가 일치하지 않습니다.");
            return bindingResult;
        }
        return bindingResult;
    }

    //판매자 승인
    public void approveSeller(Member member, Apply apply) {
        Member member1 = member.toBuilder()
                .isSeller(true)
                .accountNumber(apply.getAccountNumber())
                .bankName(apply.getBankName())
                .accountHolderName(apply.getAccountHolderName())
                .build();

        this.memberRepository.save(member1);
    }
    public Member getMember(Integer id) {
        Optional<Member> member = this.memberRepository.findById(id);
        if (member.isEmpty()) {
            return null;
        }
        return member.get();
    }

    public Member getMember(Integer id) {
        Optional<Member> member = this.memberRepository.findById(id);
        if (member.isEmpty()) {
            return null;
        }
        return member.get();
    }

    // Cartitem(장바구니)
    public void addCartItem(Member loginedUser, CartItem cartItem) {
        List<CartItem> cartList = loginedUser.getCartList();
        cartList.add(cartItem);
        Member member = loginedUser.toBuilder()
                .cartList(cartList)
                .build();

        this.memberRepository.save(member);
    }

    // Wish(찜)
    public void addWish(Member loginedUser, Wish wish) {
        List<Wish> wishList = loginedUser.getWishList();
        wishList.add(wish);
        Member member = loginedUser.toBuilder()
                .wishList(wishList)
                .build();

        this.memberRepository.save(member);
    }


    public List<Member> findByIsSeller() {
        return this.memberRepository.findByIsSeller(true);
    }
}
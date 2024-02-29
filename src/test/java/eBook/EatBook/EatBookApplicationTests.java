package eBook.EatBook;

import eBook.EatBook.domain.member.DTO.MemberRegisterForm;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.domain.service_center.Entity.Faq;
import eBook.EatBook.domain.service_center.Repository.FaqRepository;
import eBook.EatBook.domain.service_center.Service.FaqService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EatBookApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MemberService memberService;
    @Autowired
    FaqRepository faqRepository;

    @Test
    void createAll() {
        //데이터 한번에 생성
        createAdmin();
        makeFaq();
    }

    @Test
    void createAdmin() {
        MemberRegisterForm memberRegisterForm = new MemberRegisterForm();
        memberRegisterForm.setUsername("admin");
        memberRegisterForm.setNickname("어드민");
        // 이메일 테스트 원할 시 수정해서 사용
        memberRegisterForm.setEmail("admin@email.com");
        memberRegisterForm.setPassword1("1234");
        memberRegisterForm.setPassword2("1234");


        this.memberService.register(memberRegisterForm);

    }

    @Test
    void makeFaq() {
        // faq 게시물 10개 생성
        Member member = memberService.findById(1);
        for (int i = 1; i <= 10; i++) {
            Faq faq = Faq.builder()
                    .title(i + "번 FAQ 제목")
                    .content(i + "번 FAQ 내용 입니다.")
                    .member(member)
                    .build();
            faqRepository.save(faq);
        }
    }


}

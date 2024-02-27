package eBook.EatBook;

import eBook.EatBook.domain.member.DTO.MemberRegisterForm;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
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

	@Test
	void createAdmin(){
		MemberRegisterForm memberRegisterForm = new MemberRegisterForm();
		memberRegisterForm.setUsername("admin");
		memberRegisterForm.setNickname("어드민");
		// 이메일 테스트 원할 시 수정해서 사용
		memberRegisterForm.setEmail("admin@email.com");
		memberRegisterForm.setPassword1("1234");
		memberRegisterForm.setPassword2("1234");

		this.memberService.register(memberRegisterForm);

	}

}

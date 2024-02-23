package eBook.EatBook.domain.member.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegisterForm {
    @NotEmpty(message = "유저이름은 필수항목입니다.")
    private String username;

    @NotEmpty(message = "닉네임은 필수항목입니다.")
    private String nickname;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 재확인을 해주세요.")
    private String password2;
}

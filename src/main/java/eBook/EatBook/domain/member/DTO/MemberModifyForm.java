package eBook.EatBook.domain.member.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberModifyForm {

    @NotEmpty(message = "닉네임은 필수 입니다.")
    private String nickname;

    private String password1;

    private String password2;

    @NotEmpty(message = "이메일은 필수 입니다.")
    @Email
    private String email;

}

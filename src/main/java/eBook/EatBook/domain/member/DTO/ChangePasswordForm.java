package eBook.EatBook.domain.member.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordForm {

    @NotEmpty(message = "이전 비밀번호를 입력해주세요")
    private String previousPassword;

    @NotEmpty(message = "새로운 비밀번호를 입력해주세요")
    private String newPassword1;

    @NotEmpty(message = "새로운 비밀번호 확인을 입력해주세요")
    private String newPassword2;
}

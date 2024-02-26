package eBook.EatBook.domain.member.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindPasswordForm {
    @NotEmpty(message = "아이디를 입력해주세요.")
    private String username;

    @NotEmpty(message = "이메일을 입력해주세요.")
    private String toEmail;
}

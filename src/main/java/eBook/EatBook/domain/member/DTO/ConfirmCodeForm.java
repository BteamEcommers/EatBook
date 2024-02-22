package eBook.EatBook.domain.member.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmCodeForm {
    @NotEmpty(message = "확인번호를 입력해주세요")
    private String confirmCode;
}

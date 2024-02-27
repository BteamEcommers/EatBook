package eBook.EatBook.domain.apply.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplySellerForm {
    @NotEmpty(message = "판매자 소개를 작성해주세요")
    private String sellerIntroduce;

    @NotEmpty(message = "계좌번호를 입력해주세요")
    private String accountNumber;
}

package eBook.EatBook.domain.apply.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplySellerForm {
    @NotEmpty(message = "판매자 소개를 작성해주세요")
    private String sellerIntroduce;

    @NotEmpty(message = "금융기관명을 입력해주세요")
    private String bankName;

    @NotEmpty(message = "계좌번호를 입력해주세요")
    private String accountNumber;

    // 예금주
    @NotEmpty(message = "예금주를 입력해주세요")
    private String accountHolderName;
}

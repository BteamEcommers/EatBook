package eBook.EatBook.domain.review.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewForm {

    @NotEmpty(message = "내용을 입력해주세요")
    private String content;

    private float rating;

    private Integer bookId;

    private String nickname;

}

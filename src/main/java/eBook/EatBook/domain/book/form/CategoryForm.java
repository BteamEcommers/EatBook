package eBook.EatBook.domain.book.form;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryForm {
    @NotEmpty(message = "카테고리는 필수입력 사항입니다.")
    private String category;

    private String bestSeller;
    private String foreignBook;
    private String koreanBook;
    private String freeBook;
    private String newBook;


    private String selectedValue;

    private String subject;

    private String radioButtonValue;

    private String categoryType;
}

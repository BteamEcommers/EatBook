package eBook.EatBook.domain.book.form;

import eBook.EatBook.domain.category.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;




@Getter
@Setter
public class BookForm {
    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=200)
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;

    private Category category;
    private Integer price;
    private String publisher;
    private String bookIntroduce;
    private String author;
    private float discount;
}

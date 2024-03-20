package eBook.EatBook.domain.event.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventForm {

    private String title;


    private String content;

    private String thumbnailImg;

}

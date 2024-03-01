package eBook.EatBook.domain.event.Entity;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity {

    private String title;
    private String content;

    @ManyToOne
    private Member member;

}

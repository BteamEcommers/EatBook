package eBook.EatBook.report.entity;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.review.entity.Review;
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
@AllArgsConstructor
@NoArgsConstructor
public class Report extends BaseEntity {
    @ManyToOne
    private Member author;
    @ManyToOne
    private Review review;
    private String subject;
    private String content;
    private String radioButtonValue;
    private String reportType;
}

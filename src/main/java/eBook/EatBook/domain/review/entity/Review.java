package eBook.EatBook.domain.review.entity;

import eBook.EatBook.domain.book.entity.Book;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.global.baseEntity.BaseEntity;
import eBook.EatBook.report.entity.Report;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {
    @ManyToOne
    private Book book;

    @ManyToOne
    private Member author;

    @Column
    private String content;

    @Column
    private float rating; // 별점

    @ManyToMany
    private Set<Member> voter;

    @CreatedDate
    private LocalDateTime createDate;

    public void setContent(String content) {
        this.content = content;
    }

    public void setModifiedDate(LocalDateTime now) {
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<Report> report;

    private String reportType;

    @Column
    private String radioButtonValue;
    private String subject;

}

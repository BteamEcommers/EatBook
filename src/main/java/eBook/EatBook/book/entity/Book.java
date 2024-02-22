package eBook.EatBook.book.entity;


import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.global.baseEntity.BaseEntity;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Book extends BaseEntity {

    @ManyToOne
    private Member seller;

    @ManyToOne
    private Category category;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String publisher;
    @Column
    private String bookIntroduce;
    @Column
    private String bookThumbnailImg;
    @Column
    private Integer price;
    @Column
    private float discount;
    @Column
    private float averageRating;
    @Column
    private Integer sellCount;
    @Column
    private char ISBN;

}

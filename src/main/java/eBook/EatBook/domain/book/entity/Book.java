package eBook.EatBook.domain.book.entity;


import eBook.EatBook.domain.category.entity.Category;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.global.baseEntity.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Transient
    private MultipartFile image;    //MultipartFile 은 파일 업로드시에 필요하다 해서 사용합니다.

    @Column
    private String radioButtonValue;

}


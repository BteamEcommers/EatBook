package eBook.EatBook.domain.event.Entity;


import eBook.EatBook.domain.coupon.Entity.Coupon;
import eBook.EatBook.domain.member.entity.Member;


import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Column;
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
    private String thumbnailImg;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Coupon coupon;



}

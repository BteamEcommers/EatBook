package eBook.EatBook.domain.coupon.Entity;


import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends BaseEntity {
    private String title;
    private String content;
    private Integer discount_price;
    private Integer discount_rate;
    private String thumbnailImg;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}

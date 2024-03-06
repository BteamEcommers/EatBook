package eBook.EatBook.domain.coupon.Entity;

import eBook.EatBook.domain.event.Entity.Event;
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
    private String couponName;
    private Integer discountPrice;
    private float discountRate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    // 이벤트로 임시 (변경필요)
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


}

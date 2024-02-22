package eBook.EatBook.domain.coupon.Entity;

import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
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
    private float discountRate;
}

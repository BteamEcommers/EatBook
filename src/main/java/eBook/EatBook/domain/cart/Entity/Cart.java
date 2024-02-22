package eBook.EatBook.domain.cart.Entity;


import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity {
    private Integer total_price;
    private Integer total_discount;
}

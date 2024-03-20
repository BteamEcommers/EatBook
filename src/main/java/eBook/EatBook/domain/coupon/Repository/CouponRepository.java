package eBook.EatBook.domain.coupon.Repository;

import eBook.EatBook.domain.coupon.Entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}

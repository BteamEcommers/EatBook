package eBook.EatBook.domain.coupon.Repository;


import eBook.EatBook.domain.coupon.Entity.Coupon;
import eBook.EatBook.domain.coupon.Entity.GetCoupon;
import eBook.EatBook.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetCouponRepository extends JpaRepository<GetCoupon, Integer> {
    boolean existsByMemberAndCoupon(Member member, Coupon coupon);
}

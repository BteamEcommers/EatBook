package eBook.EatBook.domain.coupon.Service;

import eBook.EatBook.domain.coupon.Entity.Coupon;
import eBook.EatBook.domain.coupon.Entity.GetCoupon;
import eBook.EatBook.domain.coupon.Repository.GetCouponRepository;
import eBook.EatBook.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetCouponService {
    private final GetCouponRepository getCouponRepository;


    public GetCoupon addCoupon(Member member, Coupon coupon) {
        GetCoupon getCoupon = GetCoupon.builder()
                .member(member)
                .coupon(coupon)
                .build();

        this.getCouponRepository.save(getCoupon);

        return getCoupon;
    }


    public boolean hasCoupon(Member member, Coupon coupon) {
        return this.getCouponRepository.existsByMemberAndCoupon(member, coupon);
    }


    public List<Coupon> findaddCouponByCoupon(List<GetCoupon> getCouponList) {
        List<Coupon> couponList = new ArrayList<>();
        for(int i = 0; i < getCouponList.size(); i++) {
            couponList.add(getCouponList.get(i).getCoupon());
        }
        return couponList;
    }
}

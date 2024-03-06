package eBook.EatBook.domain.coupon.Service;

import eBook.EatBook.domain.coupon.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;



}

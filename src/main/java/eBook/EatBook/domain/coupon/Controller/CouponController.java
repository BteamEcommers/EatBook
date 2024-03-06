package eBook.EatBook.domain.coupon.Controller;

import eBook.EatBook.domain.coupon.Service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

}

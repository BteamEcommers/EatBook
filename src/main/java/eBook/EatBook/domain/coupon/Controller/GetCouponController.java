package eBook.EatBook.domain.coupon.Controller;

import eBook.EatBook.domain.coupon.Entity.Coupon;
import eBook.EatBook.domain.coupon.Entity.GetCoupon;
import eBook.EatBook.domain.coupon.Service.CouponService;
import eBook.EatBook.domain.coupon.Service.GetCouponService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/coupon")
public class GetCouponController {
    private final GetCouponService getCouponService;
    private final MemberService memberService;
    private final CouponService couponService;

    @GetMapping("/add/{id}")
    public String addCoupon(@PathVariable(value = "id") Integer id, Principal principal) {
        Member member = this.memberService.findByUsername(principal.getName());
        Coupon coupon = this.couponService.getCoupon(id);

         //중복 체크: 해당 쿠폰이 쿠폰함에 이미 존재하는지 확인
        if (getCouponService.hasCoupon(member, coupon)) {
            // 이미 쿠폰함에 있으면 중복으로 추가하지 않고 쿠폰 리스트 페이지로 리다이렉트
            return "redirect:/coupon/list";
        }
        GetCoupon getCoupon = this.getCouponService.addCoupon(member, coupon);
        this.memberService.addCoupon(member, getCoupon);

        return "redirect:/coupon/list";

    }

    @GetMapping("/CouponList")
    public String addCouponList(Model model, Principal principal) {
        if(principal==null) {
            return "redirect:/member/login";
        }
        Member member = this.memberService.findByUsername(principal.getName());

        List<Coupon> couponList = this.getCouponService.findaddCouponByCoupon(member.getGetCouponList());
        model.addAttribute("couponList",couponList);
        return "/coupon/coupon_member";
    }



}

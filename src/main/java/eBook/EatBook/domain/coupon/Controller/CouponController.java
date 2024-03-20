package eBook.EatBook.domain.coupon.Controller;
import eBook.EatBook.domain.coupon.DTO.CouponForm;
import eBook.EatBook.domain.coupon.Entity.Coupon;
import eBook.EatBook.domain.coupon.Service.CouponService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;
    private final MemberService memberService;
    @GetMapping("/list")
    public String listCoupon(Model model) {
        List<Coupon> couponList = this.couponService.findAllCoupon();
        model.addAttribute("couponList", couponList);
        return "/coupon/coupon_list";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String createCoupon(CouponForm couponForm) {
        return "/coupon/coupon_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createCoupon(@Valid CouponForm couponForm, BindingResult bindingResult, Principal principal,
                               @RequestParam(value = "thumbnailImg", required = false) MultipartFile thumbnailImg) {
        Member member = this.memberService.getMember(principal.getName());

        try {
            if (thumbnailImg == null) {
                throw new IllegalArgumentException("이미지가 없습니다.");
            }

            this.couponService.create(couponForm, member, thumbnailImg);
            return "redirect:/coupon/list";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Coupon coupon = this.couponService.getCoupon(id);
        model.addAttribute("coupon", coupon);
        return "/coupon/coupon_detail";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCoupon(@PathVariable("id") Integer id, Principal principal) {
        Coupon coupon = this.couponService.getCoupon(id);
        this.couponService.delete(coupon);
        return "redirect:/coupon/list";
    }


}

package eBook.EatBook.domain.coupon.Service;


import eBook.EatBook.domain.coupon.DTO.CouponForm;
import eBook.EatBook.domain.coupon.Entity.Coupon;
import eBook.EatBook.domain.coupon.Repository.CouponRepository;
import eBook.EatBook.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    @Value("${custom.fileDirPath}")
    private String fileDirPath;


    public List<Coupon> findAllCoupon() {
        return this.couponRepository.findAll();
    }

    public void create(CouponForm couponForm, Member member, MultipartFile thumbnailImg) throws IOException{
        String thumbnailRelPath = "coupon/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(fileDirPath + "/" + thumbnailRelPath);

        try {
            thumbnailImg.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Coupon coupon = Coupon.builder()
                .member(member)
                .title(couponForm.getTitle())
                .content(couponForm.getContent())
                .discount_price(couponForm.getDiscount_price())
//                .discount_rate(couponForm.getDiscount_rate())
                .thumbnailImg(thumbnailRelPath)
                .build();
        this.couponRepository.save(coupon);
    }


    public Coupon getCoupon(Integer id) {
        Optional<Coupon> coupon = this.couponRepository.findById(id);
        if (coupon.isEmpty()) {
            return null;
        }
        return coupon.get();
    }

    public void delete(Coupon coupon) {
        this.couponRepository.delete(coupon);
    }





}

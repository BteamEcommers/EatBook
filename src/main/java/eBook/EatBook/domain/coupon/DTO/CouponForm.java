package eBook.EatBook.domain.coupon.DTO;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponForm {

    private String title;
    private String content;
    private Integer coupon_count;
    private Integer discount_price;
    private Integer discount_rate;
    private String thumbnailImg;
}

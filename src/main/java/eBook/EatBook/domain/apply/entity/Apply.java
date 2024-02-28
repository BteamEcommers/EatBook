package eBook.EatBook.domain.apply.entity;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Apply extends BaseEntity {

    @Column
    private String sellerIntroduce;

    @Column
    private String accountNumber;

    // 은행이름
    @Column
    private String bankName;

    // 예금주
    @Column
    private String accountHolderName;

    // 신청인
    @ManyToOne
    private Member applicant;

    // 승인 여부
    @Column
    private boolean isConfirm;
}

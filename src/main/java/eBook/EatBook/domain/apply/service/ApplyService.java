package eBook.EatBook.domain.apply.service;

import eBook.EatBook.domain.apply.DTO.ApplySellerForm;
import eBook.EatBook.domain.apply.entity.Apply;
import eBook.EatBook.domain.apply.repository.ApplyRepository;
import eBook.EatBook.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;

    public void apply(ApplySellerForm applySellerForm, Member member) {
        Apply apply = Apply.builder()
                .sellerIntroduce(applySellerForm.getSellerIntroduce())
                .bankName(applySellerForm.getBankName())
                .accountNumber(applySellerForm.getAccountNumber())
                .accountHolderName(applySellerForm.getAccountHolderName())
                .applicant(member)
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        this.applyRepository.save(apply);
    }
}

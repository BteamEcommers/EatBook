package eBook.EatBook.domain.apply.service;

import eBook.EatBook.domain.apply.DTO.ApplySellerForm;
import eBook.EatBook.domain.apply.entity.Apply;
import eBook.EatBook.domain.apply.repository.ApplyRepository;
import eBook.EatBook.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Apply> getApplyList() {

        return this.applyRepository.findAll();
    }

    public void approve(Apply apply) {
        Apply apply1 = apply.toBuilder()
                .isApprove(true)
                .build();

        this.applyRepository.save(apply1);
    }

    public Apply getApply(Integer applyId) {
        Optional<Apply> optionalApply = this.applyRepository.findById(applyId);
        if(optionalApply.isEmpty()){
            throw new RuntimeException();
        }
        return optionalApply.get();
    }

    public void delete(Integer applicantId) {
        this.applyRepository.deleteById(applicantId);
    }
}

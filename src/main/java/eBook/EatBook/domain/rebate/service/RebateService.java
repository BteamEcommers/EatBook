package eBook.EatBook.domain.rebate.service;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.rebate.entity.Rebate;
import eBook.EatBook.domain.rebate.repository.RebateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RebateService {
    private final RebateRepository rebateRepository;

//    public void createRebate(RebateForm rebateForm){
//        Rebate rebate = Rebate.builder()
//                .totalFee()
//                .totalPay()
//                .member()
//                .build();
//
//    }

    public Rebate getRebate() {
        Optional<Rebate> optionalRebate = this.rebateRepository.findById(1);
        return optionalRebate.get();
    }

    public List<Rebate> findRebateListByMember(Member seller){
        List<Rebate> rebateList = this.rebateRepository.findAllByMember(seller);
        if(rebateList.isEmpty()){
            return null;
        }

        return rebateList;
    }
}

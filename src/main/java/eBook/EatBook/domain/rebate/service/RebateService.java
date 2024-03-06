package eBook.EatBook.domain.rebate.service;

import eBook.EatBook.domain.rebate.entity.Rebate;
import eBook.EatBook.domain.rebate.repository.RebateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RebateService {
    private final RebateRepository rebateRepository;

    public Rebate getRebate() {
        Optional<Rebate> optionalRebate = this.rebateRepository.findById(1);
        return optionalRebate.get();
    }
}

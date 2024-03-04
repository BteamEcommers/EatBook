package eBook.EatBook.domain.rebate.service;

import eBook.EatBook.domain.rebate.repository.RebateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RebateService {
    private final RebateRepository rebateRepository;
}

package eBook.EatBook.domain.rebate.repository;

import eBook.EatBook.domain.rebate.entity.Rebate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebateRepository extends JpaRepository<Rebate, Integer> {
}

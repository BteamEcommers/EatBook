package eBook.EatBook.global.email.repository;

import eBook.EatBook.global.email.entity.Email1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email1, Integer> {
    Optional<Email1> findByConfirmCode(String confirmCode);
}

package eBook.EatBook.domain.service_center.Repository;


import eBook.EatBook.domain.service_center.Entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Integer> {
}

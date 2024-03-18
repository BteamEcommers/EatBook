package eBook.EatBook.report.Repository;

import eBook.EatBook.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ReportRepository extends JpaRepository<Report,Integer> {
}

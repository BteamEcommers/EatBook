package eBook.EatBook.report.service;


import eBook.EatBook.domain.review.entity.Review;
import eBook.EatBook.report.Repository.ReportRepository;
import eBook.EatBook.report.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public List<Report> getReport(){

        return this.reportRepository.findAll();
    }
    public Report getReportList(Integer id){
        Optional<Report> report = this.reportRepository.findById(id);
        if (report.isEmpty()){
            return null;
        }
        return report.get();
    }
    public void delete(Report report) {
        reportRepository.delete(report);
    }
}

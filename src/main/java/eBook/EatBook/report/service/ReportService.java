package eBook.EatBook.report.service;


import eBook.EatBook.report.Repository.ReportRepository;
import eBook.EatBook.report.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public List<Report> getReport(){
        return this.reportRepository.findAll();
    }
}

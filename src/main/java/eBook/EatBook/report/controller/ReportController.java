package eBook.EatBook.report.controller;

import eBook.EatBook.report.entity.Report;
import eBook.EatBook.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;


    @GetMapping("/report/list")
    public String reportList(Model model, Principal principal){
        if(!principal.getName().equals("admin")){
            return "redirect:/";
        }
        List<Report> reportList= this.reportService.getReport();

        model.addAttribute("reportList", reportList);

        return "book/report_list";
    }
}

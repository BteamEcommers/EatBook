package eBook.EatBook.report.controller;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import eBook.EatBook.report.entity.Report;
import eBook.EatBook.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final MemberService memberService;


    @GetMapping("/report/list")
    public String reportList(Model model, Principal principal){
        if(!principal.getName().equals("admin")){
            return "redirect:/";
        }
        List<Report> reportList= this.reportService.getReport();

        model.addAttribute("reportList", reportList);

        return "book/report_list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/report/delete/{id}")
    public String reportDelete(@PathVariable("id") Integer id,Principal principal){

        Report report = this.reportService.getReportList(id);

        Member member =  this.memberService.findByUsername(principal.getName());
        if (member == null) {
            // 작성자를 찾을 수 없는 경우에 대한 처리
            return "redirect:/book/list"; // 예시로 홈 페이지로 리다이렉트
        }
        this.reportService.delete(report);

        return "redirect:/report/list";
    }
}

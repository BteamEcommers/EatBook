package eBook.EatBook.domain.service_center.Controller;

import eBook.EatBook.domain.service_center.Service.Service_centerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faq")
public class Service_centerController {

    private final Service_centerService serviceCenterService;

    @GetMapping("/list")
    public String list() {
        return "/faq/faq_list";
    }

    @GetMapping("/create")
    public String create () {
        return "/faq/faq_form";
    }
    @PostMapping("/create")
    public String create (@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        this.serviceCenterService.create(title,content);
        return "redirect:/faq/faq_list";
    }
}

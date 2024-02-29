package eBook.EatBook.domain.home.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "mainhome";
    }

    @GetMapping("/test")
    public String test() {

        return "test";
    }


}

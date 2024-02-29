package eBook.EatBook.domain.event.Controller;

import eBook.EatBook.domain.event.Service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/list")
    public String eventList () {
        return "/event/event_list";
    }
}

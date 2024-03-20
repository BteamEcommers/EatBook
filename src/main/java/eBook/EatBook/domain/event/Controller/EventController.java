package eBook.EatBook.domain.event.Controller;

import eBook.EatBook.domain.event.DTO.EventForm;
import eBook.EatBook.domain.event.Entity.Event;
import eBook.EatBook.domain.event.Service.EventService;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final MemberService memberService;


    @GetMapping("/list")
    public String eventList(@PageableDefault(size = 9) Pageable pageable, Model model) {
        Page<Event> eventPage = this.eventService.findAllEvent(pageable);

        model.addAttribute("eventList", eventPage.getContent());
        model.addAttribute("currentPage", eventPage.getNumber());
        model.addAttribute("totalPages", eventPage.getTotalPages());

        return "/event/event_list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String eventCreate(EventForm eventForm) {
        return "/event/event_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String eventCreate(@Valid EventForm eventForm, BindingResult bindingResult, Principal principal,
                              @RequestParam(value = "thumbnailImg", required = false) MultipartFile thumbnailImg) {
        Member member = this.memberService.getMember(principal.getName());

        try {
            if (thumbnailImg == null) {
                throw new IllegalArgumentException("이미지가 없습니다.");
            }

            this.eventService.create(eventForm, member, thumbnailImg);
            return "redirect:/event/list";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/modify/{id}")
    public String eventModify(@PathVariable("id") Integer id, EventForm eventForm, Model model) {
        Event event = this.eventService.getEvent(id);
        model.addAttribute("event", event);
        eventForm.setTitle(event.getTitle());
        eventForm.setContent(event.getContent());
        return "/event/event_modifyForm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/modify/{id}")
    public String eventModify(@PathVariable("id") Integer id, Model model,
                              @Valid EventForm eventForm, BindingResult bindingResult, Principal principal) {
        Member member = this.memberService.getMember(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("member", member);
            return "/event/event_modifyForm";
        }
        Event event = this.eventService.getEvent(id);
        this.eventService.modify(event, member, eventForm.getTitle(), eventForm.getContent());
        return "redirect:/event/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String eventDelete(@PathVariable("id") Integer id, Principal principal) {
        Event event = this.eventService.getEvent(id);
        this.eventService.delete(event);
        return "redirect:/event/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Event event = this.eventService.getEvent(id);
        model.addAttribute("event", event);
        return "/event/event_detail";
    }

}

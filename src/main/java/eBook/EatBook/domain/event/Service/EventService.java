package eBook.EatBook.domain.event.Service;


import eBook.EatBook.domain.event.DTO.EventForm;
import eBook.EatBook.domain.event.Entity.Event;
import eBook.EatBook.domain.event.Repository.EventRepository;
import eBook.EatBook.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public void create(EventForm eventForm, Member member) {
        Event event = Event.builder()
                .title(eventForm.getTitle())
                .content(eventForm.getContent())
                .member(member)
                .build();
        this.eventRepository.save(event);
    }
    public void modify(Event event,Member member ,String title, String content) {
        Event eventModify = event.toBuilder()
                .title(title)
                .content(content)
                .member(member)
                .build();

        this.eventRepository.save(eventModify);
    }

    public List<Event> findAllEvent(){
        return this.eventRepository.findAll();
    }
    public Event getEvent(Integer EventId) {
        Optional<Event> event = this.eventRepository.findById(EventId);
        if (event.isEmpty()) {
            return null;
        }
        return event.get();
    }

    public void delete(Event event) {
        this.eventRepository.delete(event);
    }

    public Page<Event> findAllEvent(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }
}

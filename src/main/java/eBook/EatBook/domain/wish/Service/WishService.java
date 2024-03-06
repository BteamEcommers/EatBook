package eBook.EatBook.domain.wish.Service;

import eBook.EatBook.domain.event.Entity.Event;
import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.service_center.Entity.Faq;
import eBook.EatBook.domain.wish.Entity.Wish;
import eBook.EatBook.domain.wish.Repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    public Wish addWish(Member member, Event event) {
        Wish wish = Wish.builder()
                .member(member)
                .event(event)
                .build();

        this.wishRepository.save(wish);

        return wish;
    }
    public boolean hasWish(Member member, Event event) {
        return this.wishRepository.existsByMemberAndEvent(member, event);
    }

    public List<Event> findProductByWish(List<Wish> wishList) {
        List<Event> eventList = new ArrayList<>();
        for(int i = 0; i < wishList.size(); i++) {
            eventList.add(wishList.get(i).getEvent());
        }
        return eventList;
    }
    public List<Wish> findAllWish(){
        return this.wishRepository.findAll();
    }

    public Wish getWish(Integer wishId) {
        Optional<Wish> wish = this.wishRepository.findById(wishId);
        if (wish.isEmpty()) {
            return null;
        }
        return wish.get();
    }

    public void delete(Wish wish){
        this.wishRepository.delete(wish);
    }

}

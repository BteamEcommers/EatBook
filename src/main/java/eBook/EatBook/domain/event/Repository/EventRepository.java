package eBook.EatBook.domain.event.Repository;

import eBook.EatBook.domain.event.Entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Page<Event> findAll(Pageable pageable);
}

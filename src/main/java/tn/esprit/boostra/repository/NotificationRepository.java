package tn.esprit.boostra.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.Notification;
@Repository
public interface NotificationRepository
    extends CrudRepository<Notification, Long> {
  @Query("select e from Event e where e.startDate >= ?1")
  List<Event> getUpcomingEvents(Date d);
  @Query("select e from Event e where e.startDate = CURRENT_DATE")
  List<Event> getEventsToday();
}

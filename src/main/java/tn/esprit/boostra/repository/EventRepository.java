package tn.esprit.boostra.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.Interest;

import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository  extends CrudRepository<Event, Long> {

	@Query("select e from Event e where e.interest IN ?1")
	List<Event> suggestedEvents(List<Interest> userInterests);
	
	@Query("select e from Event e where e.startDate between ?1 and ?2")
	List<Event> tomorrowEvents(Date today, Date tomorrow);

	@Query("select e from Event e where e.startDate > ?1 and e.endDate < ?2 and e.location like %?3% and e.maxParticipants - e.participantCount >= ?4 ")
	List<Event> searchEvents(Date startDate, Date endDate, String location, int nbrPart);
}

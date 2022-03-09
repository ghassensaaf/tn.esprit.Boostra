package tn.esprit.boostra.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Event;



@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
	//List<Event> findAllBystartDateLessThanEqualAndBystartDateGreaterThanEqual(Date d);

}

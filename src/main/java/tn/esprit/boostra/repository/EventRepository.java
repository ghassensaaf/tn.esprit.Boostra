package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.boostra.entity.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository  extends CrudRepository<Event, Long> {

}

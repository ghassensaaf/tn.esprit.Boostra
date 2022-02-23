package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.repository.EventRepository;

@Service
public class EventService implements IEventService {
	@Autowired
	private EventRepository er;
	@Override
	public Event addEvent(Event event) {
		return er.save(event);
	}
	@Override
	public Event updateEvent(Event event) {
		return er.save(event);
	}
	@Override
	public void deleteEvent(Event event) {
		er.delete(event);
	}
	@Override
	public List<Event> getAllEvents(){
		return (List<Event>) er.findAll();
	}
	@Override
	public Event getEvent(long eventId) {
		return er.findById(eventId).orElse(null);
	}
}

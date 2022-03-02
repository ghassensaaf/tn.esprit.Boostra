package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Event;

public interface IEventService {
	Event addEvent(Event event);
	Event updateEvent(Event event);
	void deleteEvent(Event event);
	List<Event> getAllEvents();
	Event getEvent(long eventId);
	String getTomorrowEvents();
}

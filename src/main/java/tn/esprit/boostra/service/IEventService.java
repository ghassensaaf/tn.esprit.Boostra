package tn.esprit.boostra.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import tn.esprit.boostra.entity.Event;

public interface IEventService {
	Event addEvent(Event event);
	Event updateEvent(Event event);
	void deleteEvent(Event event);
	List<Event> getAllEvents();
	Event getEvent(long eventId);
	String getTomorrowEvents() throws IOException, JSONException;
}

package tn.esprit.boostra.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.EventRepository;
import tn.esprit.boostra.repository.UserRepository;

@Service
public class EventService implements IEventService {
	@Autowired
	private EventRepository er;
	@Autowired
	private UserRepository ur;
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
	@Override
	public String getTomorrowEvents() {
		String msg = "\n\nEvents happening tomorrow : \n";
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
	    Date tomorrow = calendar.getTime();
		List<Event> events = er.tomorrowEvents(today, tomorrow);
		for (Event event : events) {
			msg+="\t * eventId : "+event.getId()+ " event title : "+ event.getName()+"\n\n";
		}
		if (events.size()==0)
			msg= "\n\n There is no events happening tomorrow\n\n";
		return msg;
	}
	
}

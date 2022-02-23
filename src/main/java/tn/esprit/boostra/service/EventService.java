package tn.esprit.boostra.service;

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

}
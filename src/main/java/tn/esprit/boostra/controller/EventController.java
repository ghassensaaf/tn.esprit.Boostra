package tn.esprit.boostra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.service.IEventService;

@RestController
public class EventController {

	@Autowired
	IEventService es;
	@PostMapping("/event/add-event")
	public Event addEvent(@RequestBody Event  event) {
		return es.addEvent(event);
	}
	@PutMapping("/event/update-event")
	public Event updateEvent(@RequestParam("eventId") long eventId, @RequestBody Event  event) {
		event.setId(eventId);
		return es.updateEvent(event);
	}
	@DeleteMapping("/event/delete-event/{eventId}")
	public void deleteEvent(@PathVariable("eventId") Long eventId) {
		Event event= es.getEvent(eventId);
		es.deleteEvent(event);
	}
	@GetMapping("/event/get-all")
	public List<Event> getAll(){
		return es.getAllEvents();
	}
	
	@GetMapping("/event/get-event/{eventId}")
	public Event getEvent(@PathVariable("eventId") Long eventId){
		return  es.getEvent(eventId);
	}
	
}

package tn.esprit.boostra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.service.EventService;

@RestController
public class EventController {

	@Autowired
	EventService es;
	@PostMapping("/event/add-event")
	public void addEvent(@RequestBody Event  event) {
		es.addEvent(event);
	}
}

package tn.esprit.boostra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.Notification;
import tn.esprit.boostra.service.INotificationService;

@RestController

public class NotificationController {
	@Autowired
	INotificationService inotificationservice;
    @PostMapping("/addNotification")
	
	public Notification addNotification(@RequestBody Notification notification) {
		inotificationservice.addNotification(notification);
		return notification;
	}
	

@GetMapping(value = "getNotificationById/{idNotification}")
@ResponseBody
public Notification getNotificationById(@PathVariable("idNotification") Long idNotification) {
	return inotificationservice.getNotificationById(idNotification);
}
@GetMapping(value = "/getAllNotifications")
@ResponseBody
public List<Notification> getAllNotification() {
	return inotificationservice.getAllNotification();
}
@PutMapping(value = "updateNotification/{id}")
public  Notification updateNotification(@RequestBody Notification notification,@RequestParam("id") Long id) {
	return inotificationservice.updateNotification(notification,id);
}
@DeleteMapping("/deleteNotificationById/{idNotification}")
@ResponseBody
public void deleteNotificationById(@PathVariable("idNotification") Long idNotification) {
	inotificationservice.deleteNotificationById(idNotification);

}
@DeleteMapping("/deleteAllNotifications")
@ResponseBody
public void deleteAllNotifications() {
	inotificationservice.deleteAllNotification();

}
@GetMapping(value = "/getTempsAttenteEvent/{idEvent}")
@ResponseBody
public String getTempsAttenteEvent(@PathVariable("idEvent")Long idEvent) {
	return inotificationservice.getTempsAttenteEvent(idEvent);
}
@Scheduled(fixedRate=10000)
public void CheckEventsToday() {
	
	List<Event> lst = inotificationservice.getEventsToday();
	
	for (Event event : lst) {
		System.out.println("  You have an event today: " + event.getDescription() + "\n" );
	}
}

@GetMapping(value = "/getUpcomingEvents")
@ResponseBody
public List<Event> getUpcomingEvents() {
	return inotificationservice.getUpcomingEvents();
}

}


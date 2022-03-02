package tn.esprit.boostra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.service.IUserService;

@RestController
public class UserController {
	@Autowired
	IUserService us;

	@PostMapping("/registration")
	public String createNewUser(@RequestBody User user) {
		String msg = "";
		User userExists = us.findUserByUserName(user.getUserName());
		if (userExists != null) {
			msg = "There is already a user registered with the user name provided";
		} else {
			us.saveUser(user);
			msg = "OK";
		}
		return msg;
	}
	@PostMapping("/joinEvent")
	public String joinEvent(@RequestParam("eventId") long eventId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		if(us.joinEvent(uname, eventId)==1)
			return "user : "+ uname + ", joined event: " + eventId;
		else if(us.joinEvent(uname, eventId)== -1)
			return "user : "+ uname + ", is already participating in the event: " + eventId;
		else
			return "event: " + eventId + " is full";
	}
	
	@PostMapping("/unjoinEvent")
	public String unjoinEvent(@RequestParam("eventId") long eventId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		if(us.unjoinEvent(uname, eventId))
			return "user : " + uname + ", unjoined event: " + eventId;
		else 
			return "user : " + uname + ", is not participating in the event: " + eventId;
	}
	@PostMapping("/joinActivity")
	public String joinActivity(@RequestParam("activityId") long activityId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		if(us.joinActivity(uname, activityId)==1)
			return "user : "+ uname + ", joined Activity: " + activityId;
		else if(us.joinActivity(uname, activityId)== -1)
			return "user : "+ uname + ", is already participating in the Activity: " + activityId;
		else
			return "Activity: " + activityId + " is full";
	}
	
	@PostMapping("/unjoinActivity")
	public String unjoinActivity(@RequestParam("activity") long activityId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		if(us.unjoinActivity(uname, activityId))
			return "user : " + uname + ", unjoined activity: " + activityId;
		else 
			return "user : " + uname + ", is not participating in the activity: " + activityId;
	}
	@GetMapping("/suggestedEvents")
	public List<Event> suggestedEvents(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		return us.suggestEvent(uname);
	}
}

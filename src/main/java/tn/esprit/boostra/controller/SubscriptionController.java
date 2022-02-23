package tn.esprit.boostra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.Subscription;
import tn.esprit.boostra.service.ISubscriptionService;
import tn.esprit.boostra.service.IUserService;

@RestController
public class SubscriptionController {

	@Autowired
	ISubscriptionService sr;
	@Autowired
	IUserService ur;
	@PostMapping("/subscription/subscribe")
	public Subscription SubscribeToActivity(@RequestBody Subscription  subscription, @RequestParam("activityId") long activityId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		return sr.addSubscription(subscription, uname, activityId);
	}
	
	@PostMapping("/subscription/unsubscribe")
	public void UnsubscribeToActivity(@RequestParam("activityId") long activityId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		sr.unsubscribe(uname, activityId);
	}
}

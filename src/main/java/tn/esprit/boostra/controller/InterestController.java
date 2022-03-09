package tn.esprit.boostra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.Interest;
import tn.esprit.boostra.service.IInterestService;

@RestController
@RequestMapping("/interest")
public class InterestController {
	@Autowired
	IInterestService is;
	@PostMapping("/add-interest")
	public Interest addInterest(@RequestBody Interest  interest) {
		return is.addInterest(interest);
	}
	@PutMapping("/update-interest")
	public Interest updateInterest(@RequestParam("interestId") long interestId, @RequestBody Interest  interest) {
		interest.setId(interestId);
		return is.updateInterest(interest);
	}
	@DeleteMapping("/delete-interest/{interestId}")
	public void deleteInterest(@PathVariable("interestId") long interestId) {
		Interest interest= is.getInterest(interestId);
		is.deleteInterest(interest);
	}
	@GetMapping("/get-all")
	public List<Interest> getAll(){
		return is.getAllInterests();
	}
	
	@GetMapping("/{interestId}")
	public Interest getInterest(@PathVariable("interestId") long interestId){
		return  is.getInterest(interestId);
	}
	
	@PostMapping("/addUserInterest")
	public void addUserInterst(@RequestParam("interestId") long interestId ) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		is.addUserInterest(interestId, uname);
	}
	@PostMapping("/removeUserInterest")
	public void removeUserInterst(@RequestParam("interestId") long interestId ) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		is.removeUserInterest(interestId, uname);
	}
	@PostMapping("/addEventInterest")
	public void addEventInterest(@RequestParam("interestId") long interestId, @RequestParam("eventId") long eventId) {
		is.addEventInterest(interestId, eventId);
	}
	@PostMapping("/removeEventInterest")
	public void removeEventInterest(@RequestParam("eventId") long eventId) {
		is.removeEventInterest(	eventId);
	}
}

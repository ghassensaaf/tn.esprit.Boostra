package tn.esprit.boostra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.Badge;
import tn.esprit.boostra.service.IBadgeService;

@RestController
public class BadgeController {
	@Autowired
	IBadgeService badgeservice;
	
	@PostMapping("/addBadge")
	public Badge addBadge(@RequestBody Badge badge) {
		return badgeservice.addBadge(badge);
	}
	@PutMapping("/updatebadge/{id}")
	public Badge updateBadge(@RequestBody Badge badge,@RequestParam("id") Long id) {
		return  badgeservice.updateBadge(badge, id);
	}
	@GetMapping("/getbadge/{id}")
	public Badge retrieveBadge(@RequestParam("id") Long id) {
		return badgeservice.retrieveBadge(id);
		
	}
	@GetMapping("/getallbadge")
	public List<Badge> retrieveAllBadge() {
		return badgeservice.retrieveAllBadge();
	}
	@DeleteMapping("/deletebadge/{id}")
	public void deleteBadge(@RequestParam("id")Long id) {
		badgeservice.deleteBadge(id);
	}
	@DeleteMapping("/deleteallbadge")
	public void deleteAllBadge() {
		badgeservice.deleteAllBadge();
	}

}

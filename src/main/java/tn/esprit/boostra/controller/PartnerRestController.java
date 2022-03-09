package tn.esprit.boostra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import tn.esprit.boostra.entity.Partner;
import tn.esprit.boostra.service.IPartnerService;

@RestController
public class PartnerRestController {
	
	@Autowired
	IPartnerService ps; 
	
	@PostMapping("/partner/Add")
	public Partner addPartnere(@RequestBody Partner partner) {
		return ps.addPartnere(partner);
	}
	
	@PutMapping("partner/Update")
	public Partner updatePartner(@RequestBody Partner partner , @RequestParam("partnerId") long partnerId) {
		partner.setId(partnerId);
		return ps.updatePartner(partner);
	}
	
	@DeleteMapping("/partner/Delete")
	public void deletePartner(@RequestParam("partnerId") long partnerId) {
		ps.deletePartner(partnerId);
	}
	
	@GetMapping("/partner/GetAll")
	public List<Partner> getAllPartner(){
		return ps.getAllPartner();
	}
	
	@GetMapping("/partner/GetById")
	public Partner getById(@RequestParam("partnerId") long partnerId) {
		return ps.getById(partnerId);
	}

	@PutMapping("/partner/affecter")
	public void affecterPartnerToEvent(@RequestParam("partnerId") long partnerId, @RequestParam("eventId") long eventId) {
		ps.affecterPartnerToEvent(partnerId, eventId);
	}
	
	

}

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

import tn.esprit.boostra.entity.Ad;
import tn.esprit.boostra.service.IAdService;

@RestController

public class AdController {
	@Autowired
	IAdService iAdService;
	public AdController() {
	}
	public AdController(IAdService iAdService) {
		this.iAdService=iAdService;
	}
	@PostMapping("/addAd")
	
	public Ad addAd(@RequestBody Ad ad) {
		iAdService.addAd(ad);
		return ad;
	}
	@GetMapping(value = "getAdById/{idAd}")
	@ResponseBody
	public Ad getAdvertisingById(@PathVariable("idAd") Long idAd) {
		return iAdService.getAdById(idAd);
	}
	@GetMapping(value = "/getAllAds")
	@ResponseBody
	public List<Ad> getAllAd() {
		return iAdService.getAllAd();
	}
	@PutMapping(value = "updateAd/{id}")
	public 	Ad updateAd(@RequestBody Ad ad,@RequestParam("id") Long id) {
		return iAdService.updateAd(ad,id);
	}
	@DeleteMapping("/deleteAdById/{idAd}")
	@ResponseBody
	public void deleteAdvertisingById(@PathVariable("idAd") Long idAd) {
		iAdService.deleteAdById(idAd);

	}
	@DeleteMapping("/deleteAllAds")
	@ResponseBody
	public void deleteAllAdvertisings() {
		iAdService.deleteAllAd();

	}
	@GetMapping("/AdReseaux")
	public List<Ad> AdReseaux(@RequestParam("ReseauxSociaux") String ReseauxSociaux) {
	    return iAdService.AdResaux(ReseauxSociaux);
 }
	@Scheduled(cron = "0 33 11 * * *")
	public void scheduleTaskUsingCronExpression() {
		String output = "";
		List<String> lst = iAdService.getListResaux();
	    for (String string : lst) {
	    	output = output + string + ":\n";
			List<Ad> lstAd = iAdService.AdResaux(string);
			for (Ad ad : lstAd) {
				output = output + "     Id: " + ad.getId() + ", Start Date: "+ ad.getStartDate() + ", End date: " + ad.getEndDate() + ", Event description: " + ad.getEvent().getDescription() + "\n";
			}
		}
	    System.out.println(output);
	}
	
	@PostMapping("/assignAd")
	public void addAndAssignAd(@RequestBody Ad ad, @RequestParam("eventid") Long eventid){
		iAdService.addAndAssignAd(ad, eventid);
	}
}

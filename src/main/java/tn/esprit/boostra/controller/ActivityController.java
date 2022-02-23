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

import tn.esprit.boostra.entity.Activity;
import tn.esprit.boostra.service.IActivityService;

@RestController
public class ActivityController {
	@Autowired
	IActivityService as;
	@PostMapping("/activity/add-activity")
	public Activity addActivity(@RequestBody Activity  activity) {
		return as.addActivity(activity);
	}
	@PutMapping("/activity/update-activity")
	public Activity updateActivity(@RequestParam("activityId") long activityId, @RequestBody Activity  activity) {
		activity.setId(activityId);
		return as.updateActivity(activity);
	}
	@DeleteMapping("/activity/delete-activity/{activityId}")
	public void deleteActivity(@PathVariable("activityId") Long activityId) {
		Activity activity= as.getActivity(activityId);
		as.deleteActivity(activity);
	}
	@GetMapping("/activity/get-all")
	public List<Activity> getAll(){
		return as.getAllActivities();
	}
	
	@GetMapping("/activity/get-activity/{activityId}")
	public Activity getActivity(@PathVariable("activityId") Long activityId){
		return  as.getActivity(activityId);
	}
}

package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Activity;
import tn.esprit.boostra.repository.ActivityRepository;
@Service
public class ActivityService implements IActivityService{

	@Autowired
	private ActivityRepository ar;
	@Override
	public Activity addActivity(Activity activity) {
		return ar.save(activity);
	}
	@Override
	public Activity updateActivity(Activity activity) {
		return ar.save(activity);
	}
	@Override
	public void deleteActivity(Activity activity) {
		ar.delete(activity);
	}
	@Override
	public List<Activity> getAllActivities(){
		return (List<Activity>) ar.findAll();
	}
	@Override
	public Activity getActivity(long activityId) {
		return ar.findById(activityId).orElse(null);
	}

}

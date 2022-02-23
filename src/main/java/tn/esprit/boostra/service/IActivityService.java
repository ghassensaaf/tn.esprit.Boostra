package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Activity;

public interface IActivityService {
	Activity addActivity(Activity activity);
	Activity updateActivity(Activity activity);
	void deleteActivity(Activity activity);
	List<Activity> getAllActivities();
	Activity getActivity(long activityId);
}

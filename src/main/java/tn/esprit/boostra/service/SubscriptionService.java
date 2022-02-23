package tn.esprit.boostra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Activity;
import tn.esprit.boostra.entity.Subscription;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.ActivityRepository;
import tn.esprit.boostra.repository.SubscriptionRepository;
import tn.esprit.boostra.repository.UserRepository;
@Service
public class SubscriptionService implements ISubscriptionService {

	@Autowired
	SubscriptionRepository sr;
	@Autowired
	UserRepository ur;
	@Autowired
	ActivityRepository ar;
	
	@Override
	public Subscription addSubscription(Subscription sub, String uname, long activityId) {
		// TODO Auto-generated method stub
		Activity activity = ar.findById(activityId).orElse(null);
		User user = ur.findByUserName(uname);
		sub.setActivity(activity);
		sub.setUser(user);
		return sr.save(sub);
	}

	@Override
	public void unsubscribe(String uname, long activityId) {
		User user = ur.findByUserName(uname);
		sr.delete(sr.findByUserIdAndActivityId(user.getId(), activityId));		
	}

}

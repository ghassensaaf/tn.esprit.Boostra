package tn.esprit.boostra.service;

import tn.esprit.boostra.entity.Subscription;

public interface ISubscriptionService {

	Subscription addSubscription(Subscription sub, String uname, long activityId);
	void unsubscribe(String uname, long activityId);
}

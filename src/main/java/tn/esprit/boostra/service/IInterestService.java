package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Interest;

public interface IInterestService {
	Interest addInterest(Interest interest);
	Interest updateInterest(Interest interest);
	void deleteInterest(Interest interest);
	List<Interest> getAllInterests();
	Interest getInterest(long interestId);
	void addUserInterest(long interestId, String uname);
	void removeUserInterest(long interestId, String uname);
	void addEventInterest(long interestId, long eventId);
	void removeEventInterest(long eventId);
}

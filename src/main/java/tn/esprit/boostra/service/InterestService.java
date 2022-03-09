package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.Interest;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.ActivityRepository;
import tn.esprit.boostra.repository.EventRepository;
import tn.esprit.boostra.repository.InterestRepository;
import tn.esprit.boostra.repository.UserRepository;
@Service
public class InterestService implements IInterestService {

	@Autowired
	private InterestRepository ir;
	@Autowired
	private UserRepository ur;
	@Autowired
	private EventRepository er;
	@Autowired
	private ActivityRepository ar;
	@Override
	public Interest addInterest(Interest interest) {
		return ir.save(interest);
	}

	@Override
	public Interest updateInterest(Interest interest) {
		return ir.save(interest);
	}

	@Override
	public void deleteInterest(Interest interest) {
		ir.delete(interest);
	}

	@Override
	public List<Interest> getAllInterests() {
		// TODO Auto-generated method stub
		return (List<Interest>) ir.findAll();
	}

	@Override
	public Interest getInterest(long interestId) {
		// TODO Auto-generated method stub
		return ir.findById(interestId).orElse(null);
	}

	@Override
	public void addUserInterest(long interestId, String uname) {
		User user 	= ur.findByUserName(uname);
		Interest interest = ir.findById(interestId).orElse(null);
		user.getInterests().add(interest);
		ur.save(user);
		
	}

	@Override
	public void removeUserInterest(long interestId, String uname) {
		User user 	= ur.findByUserName(uname);
		Interest interest = ir.findById(interestId).orElse(null);		
		user.getInterests().remove(interest);
		ur.save(user);
	}

	@Override
	public void addEventInterest(long interestId, long eventId) {
		Event event = er.findById(eventId).orElse(null);
		Interest interest = ir.findById(interestId).orElse(null);		
		event.setInterest(interest);
		er.save(event);
	}

	@Override
	public void removeEventInterest(long eventId) {
		Event event = er.findById(eventId).orElse(null);
		event.setInterest(null);
		er.save(event);
		
	}

}

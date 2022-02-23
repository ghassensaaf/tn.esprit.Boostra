package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.EventRepository;
import tn.esprit.boostra.repository.UserRepository;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepository ur;
	@Autowired
	private EventRepository er;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public User saveUser(User u) {
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		u.setActive(true);
		return ur.save(u);
	}
	@Override
	public User findUserByUserName(String uName) {
		return ur.findByUserName(uName);
	}
	@Override
	public int joinEvent(String uname, long eventId) {
		Event event = er.findById(eventId).orElse(null);
		User user 	= ur.findByUserName(uname);
		List<Event> userEvents= user.getEvents();
			if (userEvents.contains(event))
			return -1;
		else
		{
			if(event.getMaxParticipants()>event.getParticipantCount())
			{
				user.getEvents().add(event);	
				event.setParticipantCount(event.getParticipantCount()+1);
				ur.save(user);
				er.save(event);
				return 1;
			}
			else return 0;
		}
			
	}
	@Override
	public boolean unjoinEvent(String uname, long eventId) {
		Event event = er.findById(eventId).orElse(null);
		User user 	= ur.findByUserName(uname);
		List<Event> userEvents= user.getEvents();
		if (userEvents.contains(event))
		{
			user.getEvents().remove(event);
			event.setParticipantCount(event.getParticipantCount()-1);
			ur.save(user);
			er.save(event);
			return true;
		}
		else return false;
	}

}

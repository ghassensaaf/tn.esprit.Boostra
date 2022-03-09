package tn.esprit.boostra.service;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.boostra.entity.Activity;
import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.Interest;
import tn.esprit.boostra.entity.Provider;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.ActivityRepository;
import tn.esprit.boostra.repository.EventRepository;
import tn.esprit.boostra.repository.UserRepository;
@Slf4j
@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepository ur;
	@Autowired
	private EventRepository er;
	@Autowired
	private ActivityRepository ar;
	
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
	public User updateUser(User user, Long id) {
		user.setId(id);
		  return ur.save(user);	
	}
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
	@Override
	public int joinActivity(String uname, long activityId) {
		Activity activity = ar.findById(activityId).orElse(null);
		User user 	= ur.findByUserName(uname);
		List<Activity> userActivities= user.getActivities();
		if (userActivities.contains(activity))
			return -1;
		else
		{
			if(activity.getMaxParticipants()>activity.getNbrParticipants())
			{
				user.getActivities().add(activity);	
				activity.setNbrParticipants(activity.getNbrParticipants()+1);
				ur.save(user);
				ar.save(activity);
				return 1;
			}
			else return 0;
		}
	}
	@Override
	public boolean unjoinActivity(String uname, long activityId) {
		Activity activity = ar.findById(activityId).orElse(null);
		User user 	= ur.findByUserName(uname);
		List<Activity> userActivities= user.getActivities();
		if (userActivities.contains(activity))
		{
			user.getActivities().remove(activity);	
			activity.setNbrParticipants(activity.getNbrParticipants()-1);
			ur.save(user);
			ar.save(activity);
			return true;
		}
		else return false;
	}
	@Override
	public List<Event> suggestEvent(String uname) {
		User user 	= ur.findByEmail(uname);
		List<Interest> userInterests = user.getInterests();
		return er.suggestedEvents(userInterests);
	}
	@Override
	public void processOAuthPostLogin(String uname,String fname, String lname, String picture) {
        User existUser = ur.findByUserName(uname);
        log.info(uname);
        if (existUser == null) {
            User newUser = new User();
            newUser.setUserName(uname.replace("@gmail.com", ""));
            newUser.setProvider(Provider.GOOGLE);
            newUser.setActive(true);
            newUser.setEmail(uname);
            newUser.setFirstName(fname);
            newUser.setLastName(lname);
            newUser.setPicture(picture);
            ur.save(newUser);        
        }
         
    }


}

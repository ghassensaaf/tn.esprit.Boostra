package tn.esprit.boostra.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.entity.Notification;
import tn.esprit.boostra.repository.EventRepository;
import tn.esprit.boostra.repository.NotificationRepository;
@Service
public class NotificationService implements INotificationService {
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	EventRepository er;
	
	@Override
	public void addNotification(Notification notification) {
		notificationRepository.save(notification);
		
	}

	@Override
	public List<Notification> getAllNotification() {
		
		return (List<Notification>) notificationRepository.findAll();
	}

	@Override
	public Notification getNotificationById(Long id) {
		
		return notificationRepository.findById(id).get();
	}

	@Override
	public Notification updateNotification(Notification notification, Long id) {
		notification.setId(id);
		return notificationRepository.save(notification);
	}

	@Override
	public void deleteNotificationById(Long id) {
		notificationRepository.deleteById(id);
		
	}

	@Override
	public void deleteAllNotification() {
		notificationRepository.deleteAll();
		
	}

	public String getTempsAttenteEvent(Long idEvent)
	{
		Event v = (Event)er.findById(idEvent).orElse(null);
		long duree = Math.abs(v.getEndDate().getTime()-v.getStartDate().getTime());
		 duree = duree / 1000;
         int minutes = 0;
         int heures = 0;
         int jours = 0;
         while(duree >= 60)
         {
             duree -= 60;
             minutes++;
         }

         while(minutes>= 60)
         {
             minutes -= 60;
             heures++;
         }

         while (heures >= 24)
         {
             heures -= 24;
             jours++;
         }

         String attenteFinal = jours + " Jours, " + heures + " Heures, " + minutes + " Minutes, " + duree + " Secondes";
        return attenteFinal;
         

	}
	@Override
	public List<Event>  getEventsToday() {

		
		return  notificationRepository.getEventsToday();
	}
	
	@Override
	public List<Event> getUpcomingEvents() {
		return notificationRepository.getUpcomingEvents(new Date());
	}

	
}

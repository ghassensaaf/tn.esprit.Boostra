package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Notification;
import tn.esprit.boostra.repository.NotificationRepository;
@Service
public class NotificationService implements INotificationService {
	@Autowired
	NotificationRepository notificationRepository;

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

}

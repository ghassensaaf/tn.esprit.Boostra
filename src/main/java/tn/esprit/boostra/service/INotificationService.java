package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Notification;



public interface INotificationService {
	public void addNotification(Notification notification);
	public List<Notification> getAllNotification();
	public Notification getNotificationById(Long idNotif);
	public Notification updateNotification(Notification notif,Long id);
	public void deleteNotificationById(Long id);
	public void deleteAllNotification();

}

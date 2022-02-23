package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Notification;
@Repository
public interface NotificationRepository extends CrudRepository<Notification,Long> {
}

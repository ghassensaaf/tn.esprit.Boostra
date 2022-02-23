package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Subscription;
@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

	Subscription findByUserIdAndActivityId(long userId,long activityId);
}

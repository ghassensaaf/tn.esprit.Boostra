package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Activity;
@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

}

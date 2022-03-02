package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Interest;
@Repository
public interface InterestRepository extends CrudRepository<Interest, Long> {

}

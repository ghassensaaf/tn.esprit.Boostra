package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Badge;

@Repository
public interface BadgeRepository extends CrudRepository<Badge, Long>{

}

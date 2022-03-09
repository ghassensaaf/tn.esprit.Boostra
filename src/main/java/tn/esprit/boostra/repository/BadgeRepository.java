package tn.esprit.boostra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.Badge;
import tn.esprit.boostra.entity.Badge.Rank;

@Repository
public interface BadgeRepository extends CrudRepository<Badge, Long>{
	Badge findBytypeBadge(String typeBadge);
	Badge findByTypeBadgeAndRankBadge(String typeBadge,Rank rankbadge);
	

}

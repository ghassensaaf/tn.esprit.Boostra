package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Badge;
import tn.esprit.boostra.entity.Badge.Rank;

public interface IBadgeService {
	public Badge addBadge(Badge badge);
	public Badge updateBadge(Badge badge,Long id);
	public Badge retrieveBadge(Long id);
	public List<Badge>retrieveAllBadge();
	public void deleteBadge(Long id);
	public void  deleteAllBadge();
	public Badge findBytypeBadge(String badgetype);
	public Badge findBytypeBadgeAndrankbadge(String typeBadge,Rank rankbadge);


	
}

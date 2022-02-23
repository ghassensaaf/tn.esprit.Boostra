package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.Badge;

public interface IBadgeService {
	public Badge addBadge(Badge badge);
	public Badge updateBadge(Badge badge,Long id);
	public Badge retrieveBadge(Long id);
	public List<Badge>retrieveAllBadge();
	public void deleteBadge(Long id);
	public void  deleteAllBadge();

	
}

package tn.esprit.boostra.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.boostra.entity.Badge;
import tn.esprit.boostra.entity.Badge.Rank;
import tn.esprit.boostra.repository.BadgeRepository;

@Service
public class BadgeService implements IBadgeService {

  @Autowired BadgeRepository badgeRepository;
  @Override
  public Badge addBadge(Badge badge) {

    return badgeRepository.save(badge);
  }

  @Override
  public Badge updateBadge(Badge badge, Long id) {
    badge.setId(id);
    return badgeRepository.save(badge);
  }

  @Override
  public Badge retrieveBadge(Long id) {

    return badgeRepository.findById(id).get();
  }

  @Override
  public List<Badge> retrieveAllBadge() {
    List<Badge> liste = (List<Badge>)badgeRepository.findAll();
    return liste;
  }

  @Override
  public void deleteBadge(Long id) {
    badgeRepository.deleteById(id);
  }

  @Override
  public void deleteAllBadge() {
    badgeRepository.deleteAll();
  }

  @Override
  public Badge findBytypeBadge(String badgetype) {
    return badgeRepository.findBytypeBadge(badgetype);
  }

  @Override
  public Badge findBytypeBadgeAndrankbadge(String typeBadge, Rank rankbadge) {
    return badgeRepository.findByTypeBadgeAndRankBadge(typeBadge, rankbadge);
  }
}

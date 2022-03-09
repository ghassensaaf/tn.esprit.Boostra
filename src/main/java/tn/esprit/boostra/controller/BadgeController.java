package tn.esprit.boostra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.boostra.entity.Badge;
import tn.esprit.boostra.entity.Badge.Rank;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.service.IBadgeService;
import tn.esprit.boostra.service.INoteService;
import tn.esprit.boostra.service.IUserService;

@RestController
public class BadgeController {
  @Autowired IBadgeService badgeservice;
  @Autowired INoteService noteeservice;
  @Autowired IUserService userservice;

  @PostMapping("/addBadge")
  public Badge addBadge(@RequestBody Badge badge) {
    return badgeservice.addBadge(badge);
  }
  @PutMapping("/updatebadge/{id}")
  public Badge updateBadge(@RequestBody Badge badge,
                           @RequestParam("id") Long id) {
    return badgeservice.updateBadge(badge, id);
  }
  @GetMapping("/getbadge/{id}")
  public Badge retrieveBadge(@RequestParam("id") Long id) {
    return badgeservice.retrieveBadge(id);
  }
  @GetMapping("/getallbadge")
  public List<Badge> retrieveAllBadge() {
    return badgeservice.retrieveAllBadge();
  }
  @DeleteMapping("/deletebadge/{id}")
  public void deleteBadge(@RequestParam("id") Long id) {
    badgeservice.deleteBadge(id);
  }
  @DeleteMapping("/deleteallbadge")
  public void deleteAllBadge() {
    badgeservice.deleteAllBadge();
  }
  @GetMapping("/AffectBadgeToUser")
  public String AffectBadgeToUser(@RequestParam("username") String username,
                                  @RequestParam("type") String type) {
    User user = userservice.findUserByUserName(username);
    String badgelevel = noteeservice.VerifyUserNoteCompatibility(user, type);
    if (badgelevel.compareTo("NONE") != 0) {
      Badge badge = badgeservice.findBytypeBadgeAndrankbadge(
          type, Rank.valueOf(badgelevel));
      // badgeservice.updateBadge(badge, badge.getId());
      List<Badge> badges = user.getBadges();
      badges.add(badge);
      user.setBadges(badges);
      if (badgelevel.compareTo("Gold") == 0)
        user.setFidelite(user.getFidelite() + 1);
      userservice.updateUser(user, user.getId());
      return " we have assigned a badge to " + user.getUserName() +
          " of type " + type + " rank " + badgelevel;
    } else
      return " we cant assign a badge to this user";
  }
}

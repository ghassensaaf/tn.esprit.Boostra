package tn.esprit.boostra.service;

import tn.esprit.boostra.entity.User;

public interface IUserService {
 User saveUser(User U);
 User findUserByUserName(String uName);
 int joinEvent(String uname, long eventId);
 boolean unjoinEvent(String uname, long eventId);
}

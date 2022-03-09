package tn.esprit.boostra.service;

import tn.esprit.boostra.entity.User;

public interface IUserService {
 User saveUser(User U);
 User findUserByUserName(String uName);
 public User updateUser(User user, Long id);
}

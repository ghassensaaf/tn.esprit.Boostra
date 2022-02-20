package tn.esprit.boostra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.UserRepository;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepository ur;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	@Override
	public User saveUser(User u) {
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		u.setActive(true);
		return ur.save(u);
	}
	@Override
	public User findUserByUserName(String uName) {
		return ur.findByUserName(uName);
	}

}

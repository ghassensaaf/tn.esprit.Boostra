package tn.esprit.boostra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.service.UserService;

@RestController
public class HomeController {
	@Autowired
	UserService us;

	@PostMapping("/registration")
	public String createNewUser(@RequestBody User user) {
		String msg = "";
		User userExists = us.findUserByUserName(user.getUserName());
		if (userExists != null) {
			msg = "There is already a user registered with the user name provided";
		} else {
			us.saveUser(user);
			msg = "OK";
		}
		return msg;
	}

	@GetMapping("getUserById/{idUser}")
	public String getUserById(@PathVariable("idUser") int idUser) {
		return "Bonjour, je suis dans la méthode getUserById et l'id " + idUser;
	}

	@GetMapping("getAll")
	public String getAll() {
		return "Bonjour, je suis dans la méthode getAll ";
	}

	@GetMapping("sayHello")
	public String sayHello() {
		return "Bonjour, je suis dans la méthode sayHello ";
	}
}

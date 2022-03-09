package tn.esprit.boostra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.boostra.entity.NoteQuiz;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.service.INoteService;

@RestController
public class NoteController {
	@Autowired
	INoteService Noteservice;
	@GetMapping("/getNotePerUser")
	public List<NoteQuiz> retrieveNotePerUser(@RequestBody User user){
		return Noteservice.RetrieveNoteUser(user);
	}
}

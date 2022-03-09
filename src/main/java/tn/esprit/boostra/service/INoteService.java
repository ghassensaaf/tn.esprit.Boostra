package tn.esprit.boostra.service;

import java.util.List;

import tn.esprit.boostra.entity.NoteQuiz;
import tn.esprit.boostra.entity.User;

public interface INoteService {

	public NoteQuiz addNote(NoteQuiz notequiz);

	public NoteQuiz updateNote(NoteQuiz notequiz, Long id);

	public NoteQuiz retrieveNoteQuiz(Long id);

	public List<NoteQuiz> retrieveNoteQuiz();

	public void deleteNoteQuiz(Long id);

	public void deleteAllNotes();
	public List<NoteQuiz> RetrieveNoteUser(User user);
	public String VerifyUserNoteCompatibility(User userid,String typeQuiz);
	public List<NoteQuiz> getTheLastPassedQuiz(User user);
}

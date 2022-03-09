package tn.esprit.boostra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.NoteQuiz;
import tn.esprit.boostra.entity.Quiz.Level;
import tn.esprit.boostra.entity.User;
import tn.esprit.boostra.repository.NoteRepository;

@Service
public class NoteService implements INoteService{

	@Autowired
	NoteRepository noteRepository;
	@Override
	public NoteQuiz addNote(NoteQuiz notequiz) {
		return noteRepository.save(notequiz);
	}
	@Override
	public NoteQuiz updateNote(NoteQuiz notequiz, Long id) {
		notequiz.setId(id);
		  return noteRepository.save(notequiz);	
	}
	@Override
	public NoteQuiz retrieveNoteQuiz(Long id) {
		
		return noteRepository.findById(id).get();
	}
	@Override
	public List<NoteQuiz> retrieveNoteQuiz() {
		List<NoteQuiz> liste=(List<NoteQuiz>) noteRepository.findAll();
		return liste;
	}
	@Override
	public void deleteNoteQuiz(Long id) {
		noteRepository.deleteById(id);
		
	}
	@Override
	public void deleteAllNotes() {
		noteRepository.deleteAll();
		
	}
	@Override
	public List<NoteQuiz> RetrieveNoteUser(User user) {
	return noteRepository.RetrieveNotesPerUser(user);
	}
	@Override
	public String VerifyUserNoteCompatibility(User user,String typeQuiz)
	{
		List<NoteQuiz> List_notequiz=noteRepository.RetrieveNotesPerUser(user);
		boolean compatible1=false;
		boolean compatible2=false;
		boolean compatible3=false;
		//boolean compatible3=false;
		String badge="None";
		for(NoteQuiz notequiz: List_notequiz)
		{
		if(notequiz.getQuiz().getType().compareTo(typeQuiz)==0) {
			if(notequiz.getQuiz().getLevel()==Level.Hard && notequiz.getNote()==3 )
			{
				//return true;
				badge="Gold";
				return badge;
			}
			switch(notequiz.getQuiz().getLevel())
			{
			case Easy:
				if(notequiz.getNote()>=2)
					compatible1=true;
				else compatible1=false;
				break;
			case Medium:
				if(notequiz.getNote()>=2)
					compatible2=true;
				else compatible2=false;
				
				break;
			case Hard:
				if(notequiz.getNote()>=2)
					compatible3=true;
				else compatible3=false;
				break;
			}
		}
		
	}
		if(compatible1 && compatible2 && compatible3)
		{
			badge="Silver";
			return badge;
		}
		else if(compatible1 && compatible2)
		{
			badge="Bronze";
			return badge;
		}
		else return badge;
	}
	@Override
	public List<NoteQuiz> getTheLastPassedQuiz(User user) {
		return noteRepository.getTheLastPassedQuiz(user);
	}
}

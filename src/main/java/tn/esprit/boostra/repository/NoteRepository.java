package tn.esprit.boostra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.boostra.entity.NoteQuiz;
import tn.esprit.boostra.entity.User;

@Repository
public interface NoteRepository extends CrudRepository<NoteQuiz, Long>{
	@Query("SELECT u FROM NoteQuiz u WHERE u.user = ?1")
	List<NoteQuiz> RetrieveNotesPerUser(User user);
	@Query(value="SELECT u FROM NoteQuiz u WHERE u.user = ?1 ORDER BY u.id DESC ")
	List<NoteQuiz> getTheLastPassedQuiz(User user);

}

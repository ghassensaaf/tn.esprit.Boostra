package tn.esprit.boostra.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import tn.esprit.boostra.entity.Reclamation;

@Repository
public interface IReclamationService {

  public Reclamation AddReclamation(Reclamation reclamation);
  public Reclamation SetReclamtionTotraiter(Reclamation reclamation);
  public void UpdateSystemReclamation();
  void DeleteReclamation(Reclamation reclamation);
  List<Reclamation> GetAllReclamation();
  public Reclamation GeReclamationById(Long ReclamationId);
  List<Reclamation> GetReclmationByUserid();
}

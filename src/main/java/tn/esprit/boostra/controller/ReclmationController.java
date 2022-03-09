package tn.esprit.boostra.controller;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.boostra.entity.Comment;
import tn.esprit.boostra.entity.Reclamation;
import tn.esprit.boostra.service.IReclamationService;
@Slf4j
@RestController
public class ReclmationController {

  @Autowired IReclamationService rs;

  @PostMapping("/Reclamation/Add")
  public Reclamation AddReclmation(@RequestBody Reclamation reclamation) {
    return rs.AddReclamation(reclamation);
  }

  @PutMapping("/Reclamation/Update")
  public Reclamation
  UpadateReclamation(@RequestParam("ReclmationId") Long ReclmationId,
                     @RequestBody Reclamation reclamation) {
    reclamation.setId(ReclmationId);
    return rs.SetReclamtionTotraiter(reclamation);
  }

  @GetMapping("/Reclamation/GetAllReclmation")
  public List<Reclamation> GetAllReclamation() {
    return rs.GetAllReclamation();
  }

  @GetMapping("/Reclamation/GetAllReclmationByUserId")
  public List<Reclamation> GetReclmationByUserid() {
    return rs.GetReclmationByUserid();
  }
  @DeleteMapping("/Reclamation/delete/{ReclamationId}")
  public void
  DeleteReclamation(@PathVariable("ReclamationId") Long ReclamationId) {

    Reclamation reclmation = rs.GeReclamationById(ReclamationId);
    rs.DeleteReclamation(reclmation);
  }
  @Scheduled(cron = "* * */24 * * *")
  public void UpdateSystemReclamation() {
    rs.UpdateSystemReclamation();
    String text = "\n ReclmationUpdated \n";
    log.info(text);
    text = "";
  }
}

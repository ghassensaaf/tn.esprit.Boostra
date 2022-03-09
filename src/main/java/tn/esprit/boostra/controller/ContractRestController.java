package tn.esprit.boostra.controller;

import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.boostra.entity.Contract;
import tn.esprit.boostra.service.IContractService;

@RestController
@Slf4j
public class ContractRestController {

  @Autowired IContractService cs;

  @PostMapping("/contract/Add")
  public Contract addContract(@RequestBody Contract contract,
                              @RequestParam("partnerId") long partnerId)
      throws JSONException, IOException {
    log.info("coucou");
    return cs.addContract(contract, partnerId);
  }

  @PutMapping("/contract/Update")
  public Contract updateContract(@RequestBody Contract contract,
                                 @RequestParam("contractId") long contractId,
                                 @RequestParam("partnerId") long partnerId)
      throws JSONException, IOException {
    contract.setId(contractId);
    return cs.updateContract(contract, partnerId);
  }

  @DeleteMapping("/contract/Delete")
  public void deleteContract(@RequestParam("contractId") long contractId) {
    cs.deleteContract(contractId);
  }

  @GetMapping("/contract/GetAll")
  public List<Contract> getAllContract() {
    return cs.getAllContract();
  }

  @GetMapping("/contract/GetById")
  public Contract getById(@RequestParam("contractId") long contractId) {
    return cs.getById(contractId);
  }

  @Scheduled(cron = "*/3 * * * * * ")
  public void RenouvelementContrat() {
    log.info(cs.renewingContrat());
  }
}

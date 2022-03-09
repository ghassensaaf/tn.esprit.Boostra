package tn.esprit.boostra.service;

import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import tn.esprit.boostra.entity.Contract;

public interface IContractService {

  Contract addContract(Contract contract, long partnerId)
      throws JSONException, IOException;
  Contract updateContract(Contract contract, long partnerId)
      throws JSONException, IOException;
  void deleteContract(long contractId);
  List<Contract> getAllContract();
  Contract getById(long contractId);
  String renewingContrat();
}

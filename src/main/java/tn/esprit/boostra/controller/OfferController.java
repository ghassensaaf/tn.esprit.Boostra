package tn.esprit.boostra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.boostra.entity.Offer;
import tn.esprit.boostra.service.IOfferService;

@RestController
public class OfferController {

  @Autowired IOfferService os;

  @PostMapping("/Offer/Add")
  public Offer addOffer(@RequestBody Offer offer,
                        @RequestParam("partnerId") long partnerId) {
    return os.addOffer(offer, partnerId);
  }

  @PutMapping("/Offer/Update")
  public Offer updateOffer(@RequestBody Offer offer,
                           @RequestParam("offerId") long offerId,
                           @RequestParam("partnerId") long partnerId) {

    offer.setId(offerId);
    return os.updateOffer(offer, partnerId);
  }

  @DeleteMapping("/Offer/Delete")
  public void deleteOffer(@RequestParam("offerId") long offerId) {
    os.deleteOffer(offerId);
  }

  @GetMapping("/Offer/GetAll")
  public List<Offer> getAllOffer() {
    return os.getAllOffer();
  }

  @GetMapping("/offer/GetById")
  public Offer getById(@RequestParam("offerId") long offerId) {
    return os.getById(offerId);
  }
}

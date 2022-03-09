package tn.esprit.boostra.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.boostra.entity.Offer;
import tn.esprit.boostra.entity.Partner;
import tn.esprit.boostra.repository.OfferRepository;
import tn.esprit.boostra.repository.PartnerRepository;

@Service

public class OfferService implements IOfferService {

  @Autowired OfferRepository or;

  @Autowired PartnerRepository pr;

  @Override
  public Offer addOffer(Offer offer, long partnerId) {
    Partner partner = pr.findById(partnerId).orElseGet(null);
    offer.setPartner(partner);
    return or.save(offer);
  }

  @Override
  public Offer updateOffer(Offer offer, long partnerId) {
    // TODO Auto-generated method stub
    Partner partner = pr.findById(partnerId).orElseGet(null);
    offer.setPartner(partner);
    return or.save(offer);
  }

  @Override
  public void deleteOffer(long offerId) {
    // TODO Auto-generated method stub
    or.deleteById(offerId);
  }

  @Override
  public List<Offer> getAllOffer() {
    // TODO Auto-generated method stub
    return (List<Offer>)or.findAll();
  }

  @Override
  public Offer getById(long offerId) {
    // TODO Auto-generated method stub
    return or.findById(offerId).orElse(null);
  }
}

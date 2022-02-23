package tn.esprit.boostra.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Ad;
import tn.esprit.boostra.repository.AdRepository;

@Service

public class AdService implements IAdService {
           
	  @Autowired
      AdRepository adRepository;
	  
      public AdService() {
    	  
      }
	
	  public AdService(AdRepository adRepository){
		  this.adRepository=adRepository;
      }
	  
	  @Override
	  public void addAd(Ad ad) {
		 
		  adRepository.save(ad);
		  
	  }
	  
	  @Override
	  public List<Ad> getAllAd(){
		  return (List<Ad>) adRepository.findAll();
		  
	  }

	@Override
	public Ad getAdById(Long id) {
		
		return adRepository.findById(id).get();
	 }
	@Override
	public Ad updateAd(Ad ad,Long id){
//		  if (adRepository.findById(idAd).orElse(null)!=null)
		ad.setId(id);
		  return adRepository.save(ad);
		
		}
	@Override
	public void deleteAdById(Long id) {
		adRepository.deleteById(id);
		
	}
	public void deleteAllAd(){
		adRepository.deleteAll();
		
	}
}

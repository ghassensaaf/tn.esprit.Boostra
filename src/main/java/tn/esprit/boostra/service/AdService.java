package tn.esprit.boostra.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import tn.esprit.boostra.entity.Ad;
import tn.esprit.boostra.entity.Event;
import tn.esprit.boostra.repository.AdRepository;
import tn.esprit.boostra.repository.EventRepository;

@Service

public class AdService implements IAdService {
           
	  @Autowired
      AdRepository adRepository;
	  @Autowired
	  EventRepository eventRepo;
	  
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
	@Override
	public void deleteAllAd(){
		adRepository.deleteAll();
		
	}
	@Override
	public List<Ad> AdResaux(String ReseauxSociaux){
		  List<Ad> list=adRepository.test(ReseauxSociaux);
		  return list;
		
		
	}
	@Override
	public List<String> getListResaux(){
		  List<String> list=adRepository.test2();
		  return list;
		
		
	}
	@Override
	public void addAndAssignAd(Ad ad,Long eventid){
		Event ev=eventRepo.findById(eventid).get();
		ad.setEvent(ev);
		adRepository.save(ad);
		
	}
 }

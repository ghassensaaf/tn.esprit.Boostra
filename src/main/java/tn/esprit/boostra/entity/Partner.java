package tn.esprit.boostra.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import tn.esprit.boostra.entity.Contract.TypeCont;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Partner implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String name;
	String adress;
	String country;
	String email;
	String logo;
	int phone;
	
	@Enumerated(EnumType.STRING)
	TypePar typePartner;
	
	enum TypePar{
		EventPartner,ActivityPartner
	}
	
	@ManyToMany
	private List<Event> events;
	
	@OneToOne(mappedBy="partner")
    private Contract contract;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="partner")
	private List<Offer> offers;
	
}

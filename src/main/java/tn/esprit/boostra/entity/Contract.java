package tn.esprit.boostra.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contract implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@Temporal(TemporalType.DATE)
	Date startDate;
	@Temporal(TemporalType.DATE)
	Date endDate; 
	double price;
	double priceTND;
	boolean renewing; 
	boolean statut ; 
	@Enumerated(EnumType.STRING)
	Cuerrency cuerrency;
	@Enumerated(EnumType.STRING)
	
	TypeCont typeContract;
	
	public enum TypeCont{
		PerEvent,Monthly,halfYearly,Yearly
	}
	public enum Cuerrency{
		USD,TND,EUR,TRY
	}
	
	@JsonIgnore
	@OneToOne
	private Partner partner;
}

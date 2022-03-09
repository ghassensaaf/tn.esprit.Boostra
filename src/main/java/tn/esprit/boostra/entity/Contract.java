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
	Date startDate;
	Date endDate; 
	double price;
	@Enumerated(EnumType.STRING)
	Cuerrency cuerrency;
	@Enumerated(EnumType.STRING)
	TypeCont typeContract;
	
	enum TypeCont{
		PerEvent,Monthly,halfYearly,Yearly
	}
	enum Cuerrency{
		USD,TND,EUR
	}
	@OneToOne
	private Partner partner;
}

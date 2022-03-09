package tn.esprit.boostra.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Activity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String name; 
	String description;
	String picture;
	String location;
	Date startDate;
	Date endDate;
	double price;
	int maxParticipants;
	int nbrParticipants;
	
	@JsonIgnore
	@ManyToMany(mappedBy="activities", fetch = FetchType.EAGER)
	private List<User> Users;
	
	@ManyToOne	
    Interest interest;
	
}

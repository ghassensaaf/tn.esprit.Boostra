package tn.esprit.boostra.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Event implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String name;
	String description;
	String location;
	String picture;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern ="yyyy-MM-dd",shape = Shape.STRING)
	Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern ="yyyy-MM-dd",shape = Shape.STRING)
	Date endDate;
	Double Price;
	int participantCount;
	int maxParticipants;
	
	@JsonIgnore
	@ManyToMany(mappedBy="events", fetch = FetchType.EAGER)
	private List<User> Users;
	
	@ManyToMany(mappedBy="events")
	private List<Partner> partners;
	
	@OneToMany(mappedBy="event")
	@JsonIgnore
	private List<Ad> ads ;

	@JsonIgnore
	@ManyToOne	
    Interest interest;
}

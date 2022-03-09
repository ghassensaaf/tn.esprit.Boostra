package tn.esprit.boostra.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String userName;
	String firstName;
	String lastName;
	Date dateOfBirth;
	String email;
	String picture;
	String password;
	Boolean active;

	int fidelite;

	Long Number;
	@Enumerated(EnumType.STRING)
    private Provider provider;
	

	@Enumerated(EnumType.STRING)
	Gender gender;

	enum Gender{
		Male,Female
	}
	
	@ManyToMany()
	private List<Event> events;
	
	@ManyToMany()
	private List<Activity> activities;
	
	@ManyToMany()
	private List<Interest> interests;
	
	@OneToMany(mappedBy="user")
	List<Article> articles = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<Reclamation> Reclamations = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<Post> posts = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<Comment> comments= new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<NoteQuiz> notes= new ArrayList<>();
	
//	@OneToMany(mappedBy="user")
//	List<MessageDTO2> senders= new ArrayList<>();
//	
//	@OneToMany(mappedBy="user")
//	List<MessageDTO> receivers= new ArrayList<>();
	@OneToMany(mappedBy = "sender")
	
	private List<Message> messagesSent;

	@OneToMany(mappedBy = "receiver")
	
	private List<Message> messagesReceived;
	
	@OneToMany(mappedBy="user")
	List<Notification> notifications= new ArrayList<>();
		 
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Badge>badges;
	
	@ManyToMany
	private List<Search> searches;
	
	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	private Set<Role> roles;
}

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
	@Enumerated(EnumType.STRING)
	Gender gender;

	enum Gender{
		Male,Female
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Event> Events;
	
	@OneToMany(mappedBy="user")
	List<Subscription> subs;
	
	@OneToMany(mappedBy="user")
	List<Article> articles = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<Post> posts = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<Comment> comments= new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<NoteQuiz> notes= new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<Sender> senders= new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<Receiver> receivers= new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	List<Notification> notifications= new ArrayList<>();
	
	@ManyToOne
	private Badge badge;
	
	@ManyToMany
	private List<Search> searches;
	
	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	private Set<Role> roles;
}

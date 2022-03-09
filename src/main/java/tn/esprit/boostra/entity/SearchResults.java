package tn.esprit.boostra.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document("searchResults")
public class SearchResults {
	@Id
	@GeneratedValue
	String searchCode=UUID.randomUUID().toString();
	String status;
	long userId;
	String userName;
	Date dateTime;
	int count;
	List<Event> events;
}

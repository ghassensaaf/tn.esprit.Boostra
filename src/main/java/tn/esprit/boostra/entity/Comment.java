package tn.esprit.boostra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Comment implements Serializable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
  String content;
  Date date;
  boolean reply;
  int likeCount = 0;

  @JsonIgnore @ManyToOne User user;
  @JsonIgnore @ManyToOne Article article;

  @JsonIgnore @ManyToOne Post post;
}

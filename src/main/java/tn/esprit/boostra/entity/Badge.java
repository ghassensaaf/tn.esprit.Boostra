package tn.esprit.boostra.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Badge implements Serializable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
  String picture;
  String description;
  String typeBadge;
  @Enumerated(EnumType.STRING) Rank rankBadge;
  public enum Rank { Bronze, Silver, Gold }
  @ManyToMany(mappedBy = "badges") List<User> users = new ArrayList<>();
}

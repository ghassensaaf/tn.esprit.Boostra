package tn.esprit.boostra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
public class Article implements Serializable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
  @NotNull @Size(min = 5, max = 150) String title;
  @NotNull @Size(min = 5, max = 150) String content;
  Date date;
  int likeCount = 0;
  int commentCount = 0;

  @Enumerated(EnumType.STRING) TypeAr typeArticle;
  enum TypeAr { Information, Question }
  @JsonIgnore @ManyToOne private User user;
  @OneToMany(mappedBy = "article")
  private List<Comment> comments = new ArrayList<>();
}

package tn.esprit.boostra.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Quiz implements Serializable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
  String name;
  String type;
  @Enumerated(EnumType.STRING) TypeQu typeQuiz;
  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  Date startDate;
  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  Date endDate;
  int questionCount;
  String theme;
  @Enumerated(EnumType.STRING) Level level;
  public enum TypeQu { Evaluation, Knowledge }
  public enum Level { Easy, Medium, Hard, None }
  @OneToMany(mappedBy = "quiz")
  //@JsonIgnoreProperties("quiz")
  @JsonIgnore
  List<NoteQuiz> notes = new ArrayList<>();
  @OneToMany(mappedBy = "quiz")
  //@JsonIgnoreProperties("quiz")
  @JsonIgnore
  List<Question> questions;
}

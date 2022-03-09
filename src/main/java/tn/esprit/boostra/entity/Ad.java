package tn.esprit.boostra.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Ad implements Serializable {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  Date StartDate;
  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  Date endDate;
  String reseauxSociaux;
  @ManyToOne private Event event;
  public Ad(Date startDate, Date endDate) {
    super();
    StartDate = startDate;
    this.endDate = endDate;
  }
}

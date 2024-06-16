package id.co.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_tasks")
public class Complaint {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "complaint_id")
  private Integer id;

  @Column(name = "complaint_title", length = 35, nullable = false)
  private String title;

  @Column(name = "complaint_body", length = 225, nullable = false)
  private String body;

  @Column(length = 255)
  private String attachment;

  @Column(name = "complaint_date", nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date date;

  @ManyToOne
  @JoinColumn(name = "people_id", nullable = false)
  private People people;

  @ManyToOne
  @JoinColumn(name = "status_id", nullable = false)
  private Status status;

  @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<FollowUp> followUps;

  @OneToMany(
    mappedBy = "complaint",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<History> histories;
}

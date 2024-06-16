package id.co.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_complaint_fu")
public class FollowUp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "follow_up_id")
  private Integer id;

  @Column(name = "follow_up_date", nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date followUpDate;

  @Column(name = "follow_up_notes", length = 150, nullable = false)
  private String followUpNotes;

  @Column(name = "follow_up_score", length = 100)
  private String followUpScore;

  @ManyToOne
  @JoinColumn(name = "complaint_id")
  private Complaint complaint;

  @ManyToOne
  @JoinColumn(name = "officer_id")
  private User user;
}

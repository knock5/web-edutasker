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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_task_dosen")
public class TaskDosen {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "task_dosen_id")
  private Integer id;

  @Column(name = "task_title", length = 100, nullable = false)
  private String title;

  @Column(name = "task_description", nullable = false)
  private String body;

  @Column(length = 255)
  private String attachment;

  @Column(name = "start_date", nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date startDate;

  @Column(name = "due_date", nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dueDate;

  private Boolean isActive = true;

  @Column(name = "created_at")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;

  @ManyToOne
  @JoinColumn(name = "people_id", nullable = false)
  private People people;
}

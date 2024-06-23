package id.co.mii.clientapp.models;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDosen {

  private Integer id;
  private String title;
  private String body;
  private String attachment;
  private Date startDate;
  private Date dueDate;
  private Date createdAt;
  private String isActive;
  private People people;
}

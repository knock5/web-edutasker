package id.co.mii.clientapp.models;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {

  private Integer id;
  private String body;
  private String attachment;
  private Date date;
  private People people;
  private TaskDosen taskDosen;
  private Status status;
  private List<FollowUp> followUps;
  private List<History> histories;
}

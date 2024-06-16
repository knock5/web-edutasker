package id.co.mii.clientapp.models;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowUp {

  private Integer id;
  private Date followUpDate;
  private String followUpNotes;
  private Complaint complaint;
  private User user;
}

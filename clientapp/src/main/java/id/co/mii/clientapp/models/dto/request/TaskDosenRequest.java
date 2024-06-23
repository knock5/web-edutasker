package id.co.mii.clientapp.models.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDosenRequest {

  private String title;
  private String body;
  private String attachment;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date startDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dueDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;

  private Integer peopleId;
  private Boolean isActive;
}

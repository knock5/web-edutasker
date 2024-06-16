package id.co.mii.serverapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleRequest {

  private String name;
  private String email;
  private String address;
  private String phone;
  private String position;
  private String profile_picture;
  private String username;
  private String password;
}

package id.co.mii.clientapp.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {

  private Integer id;
  private String name;
  private String email;
  private String address;
  private String phone;
  private String position;
  private String profile_picture;
  private Role role;
  private List<Complaint> complaint;
  private User user;
}

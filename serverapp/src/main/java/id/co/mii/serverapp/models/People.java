package id.co.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_people")
public class People {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "people_id")
  private Integer id;

  @Column(nullable = false, length = 30)
  private String name;

  @Column(nullable = false, unique = true, length = 20)
  private String email;

  @Column(nullable = false, length = 30)
  private String address;

  @Column(nullable = false, unique = true, length = 14)
  private String phone;

  @Column(length = 50)
  private String position;

  @Column(length = 255)
  private String profile_picture;

  @OneToMany(mappedBy = "people")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Complaint> complaint;

  @OneToMany(mappedBy = "people")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<TaskDosen> taskDosens;

  @OneToOne(mappedBy = "people", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private User user;
}

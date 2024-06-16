package id.co.mii.clientapp.services;

import id.co.mii.clientapp.models.Complaint;
import id.co.mii.clientapp.models.dto.request.ComplaintRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ComplaintService {

  @Value("${server.api.url}/complaint")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<Complaint> getAll() {
    return restTemplate
      .exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Complaint>>() {}
      )
      .getBody();
  }

  public Complaint getById(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.GET, null, Complaint.class)
      .getBody();
  }

  public Complaint create(ComplaintRequest complaintRequest) {
    return restTemplate
      .exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(complaintRequest),
        Complaint.class
      )
      .getBody();
  }

  public Complaint delete(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.DELETE, null, Complaint.class)
      .getBody();
  }

  // get complaint not resolved
  public List<Complaint> findAllComplaintActive() {
    return restTemplate
      .exchange(
        url.concat("/active"),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Complaint>>() {}
      )
      .getBody();
  }

  // get complaint by user id
  public List<Complaint> getComplaintByUserId(Integer userId) {
    return restTemplate
      .exchange(
        url.concat("/userId/" + userId),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Complaint>>() {}
      )
      .getBody();
  }
}

package id.co.mii.clientapp.services;

import id.co.mii.clientapp.models.FollowUp;
import id.co.mii.clientapp.models.dto.request.FollowUpRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FollowUpService {

  @Value("${server.api.url}/followUp")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<FollowUp> getAll() {
    return restTemplate
      .exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<FollowUp>>() {}
      )
      .getBody();
  }

  public FollowUp getById(Integer id) {
    return restTemplate
      .exchange(url + "/" + id, HttpMethod.GET, null, FollowUp.class)
      .getBody();
  }

  public FollowUp create(FollowUpRequest followUpRequest) {
    return restTemplate
      .exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(followUpRequest),
        FollowUp.class
      )
      .getBody();
  }

  public FollowUp update(FollowUpRequest followUpRequest, Integer id) {
    return restTemplate
      .exchange(
        url + "/" + id,
        HttpMethod.PUT,
        null,
        FollowUp.class,
        followUpRequest
      )
      .getBody();
  }

  public FollowUp delete(Integer id) {
    return restTemplate
      .exchange(url + "/" + id, HttpMethod.DELETE, null, FollowUp.class)
      .getBody();
  }
}

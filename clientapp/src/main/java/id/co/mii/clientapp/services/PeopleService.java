package id.co.mii.clientapp.services;

import id.co.mii.clientapp.models.People;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PeopleService {

  @Value("${server.api.url}/people")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<People> getAll() {
    return restTemplate
      .exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<People>>() {}
      )
      .getBody();
  }

  public People getById(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.GET, null, People.class)
      .getBody();
  }

  public People update(Integer id, People people) {
    return restTemplate
      .exchange(
        url.concat("/" + id),
        HttpMethod.PUT,
        new HttpEntity<>(people),
        People.class
      )
      .getBody();
  }

  public People delete(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.DELETE, null, People.class)
      .getBody();
  }

  // get people by role name
  public List<People> getPeopleByRoleName(String roleName) {
    return restTemplate
      .exchange(
        url.concat("/by-role/" + roleName),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<People>>() {}
      )
      .getBody();
  }

  // get profile by username
  public People getProfileByName(String username) {
    return restTemplate
      .exchange(
        url.concat("/profile?name=" + username),
        HttpMethod.GET,
        null,
        People.class
      )
      .getBody();
  }
}

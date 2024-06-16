package id.co.mii.clientapp.services;

import id.co.mii.clientapp.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

  @Value("${server.api.url}/user")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<User> getAll() {
    return restTemplate
      .exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<User>>() {}
      )
      .getBody();
  }

  public User getById(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.GET, null, User.class)
      .getBody();
  }

  public Integer findUserIdByUsername(String username) {
    return restTemplate
      .exchange(
        url.concat("/find-id/" + username),
        HttpMethod.GET,
        null,
        Integer.class
      )
      .getBody();
  }
}

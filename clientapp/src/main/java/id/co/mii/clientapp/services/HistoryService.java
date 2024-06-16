package id.co.mii.clientapp.services;

import id.co.mii.clientapp.models.History;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HistoryService {

  @Value("${server.api.url}/history")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<History> getAll() {
    return restTemplate
      .exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<History>>() {}
      )
      .getBody();
  }

  public History getById(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.GET, null, History.class)
      .getBody();
  }

  // get history by people id
  public List<History> getHistoryByPeopleId(Integer id) {
    return restTemplate
      .exchange(
        url.concat("/user/" + id),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<History>>() {}
      )
      .getBody();
  }
}

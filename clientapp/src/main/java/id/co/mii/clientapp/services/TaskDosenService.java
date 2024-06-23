package id.co.mii.clientapp.services;

import id.co.mii.clientapp.models.TaskDosen;
import id.co.mii.clientapp.models.dto.request.TaskDosenRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TaskDosenService {

  @Value("${server.api.url}/taskdosen")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<TaskDosen> getAll() {
    return restTemplate
      .exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<TaskDosen>>() {}
      )
      .getBody();
  }

  public List<TaskDosen> getActiveTasks() {
    return restTemplate
      .exchange(
        url.concat("/active"),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<TaskDosen>>() {}
      )
      .getBody();
  }

  public List<TaskDosen> getByPeopleId(Integer id) {
    return restTemplate
      .exchange(
        url.concat("/people/" + id),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<TaskDosen>>() {}
      )
      .getBody();
  }

  public TaskDosen getById(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.GET, null, TaskDosen.class)
      .getBody();
  }

  public TaskDosen create(TaskDosenRequest taskDosenRequest) {
    return restTemplate
      .exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(taskDosenRequest),
        TaskDosen.class
      )
      .getBody();
  }

  public TaskDosen update(Integer id, TaskDosenRequest taskDosenRequest) {
    return restTemplate
      .exchange(
        url.concat("/" + id),
        HttpMethod.PUT,
        new HttpEntity<>(taskDosenRequest),
        TaskDosen.class
      )
      .getBody();
  }

  public TaskDosen delete(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.DELETE, null, TaskDosen.class)
      .getBody();
  }
}

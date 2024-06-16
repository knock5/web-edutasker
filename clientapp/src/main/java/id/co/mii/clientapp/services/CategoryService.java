package id.co.mii.clientapp.services;

import id.co.mii.clientapp.models.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryService {

  @Value("${server.api.url}/category")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<Category> getAll() {
    return restTemplate
      .exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Category>>() {}
      )
      .getBody();
  }

  public Category create(Category category) {
    return restTemplate
      .exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(category),
        Category.class
      )
      .getBody();
  }

  public Category getById(Integer id) {
    return restTemplate
      .exchange(
        url.concat("/" + id),
        HttpMethod.GET,
        null,
        Category.class
      )
      .getBody();
  }

  public Category update(Integer id, Category category) {
    return restTemplate
      .exchange(
        url.concat("/" + id),
        HttpMethod.PUT,
        new HttpEntity<>(category),
        Category.class
      )
      .getBody();
  }

  public Category delete(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.DELETE, null, Category.class)
      .getBody();
  }
}

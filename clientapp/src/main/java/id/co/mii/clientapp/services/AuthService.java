package id.co.mii.clientapp.services;

import id.co.mii.clientapp.models.People;
import id.co.mii.clientapp.models.dto.request.LoginRequest;
import id.co.mii.clientapp.models.dto.request.PeopleRequest;
import id.co.mii.clientapp.models.dto.response.LoginResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

  @Value("${server.api.url}/login")
  private String loginUrl;

  @Value("${server.api.url}/registration")
  private String regisUrl;

  @Autowired
  private RestTemplate restTemplate;

  // login user
  public Boolean login(LoginRequest loginRequest) {
    try {
      ResponseEntity<LoginResponse> response = restTemplate.exchange(
        loginUrl,
        HttpMethod.POST,
        new HttpEntity<>(loginRequest),
        LoginResponse.class
      );

      if (response.getStatusCode() == HttpStatus.OK) {
        // get body
        setPrinciple(response.getBody(), loginRequest.getPassword());
        return true;
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }

    return false;
  }

  // method set principle
  public void setPrinciple(LoginResponse loginResponse, String password) {
    List<SimpleGrantedAuthority> authorities = loginResponse
      .getAuthorities()
      .stream()
      .map(authorize -> new SimpleGrantedAuthority(authorize))
      .collect(Collectors.toList());

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
      loginResponse.getUsername(),
      password,
      authorities
    );

    //principle
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  // register user
  public People registerUser(PeopleRequest peopleRequest) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");
    return restTemplate
      .exchange(
        regisUrl,
        HttpMethod.POST,
        new HttpEntity<>(peopleRequest, headers),
        People.class
      )
      .getBody();
  }
}

package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.People;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.LoginRequest;
import id.co.mii.serverapp.models.dto.request.PeopleRequest;
import id.co.mii.serverapp.models.dto.response.LoginResponse;
import id.co.mii.serverapp.repositories.PeopleRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private PeopleRepository peopleRepository;
  private ModelMapper modelMapper;
  private RoleService roleService;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private UserRepository userRepository;
  private AppUserDetailService appUserDetailService;

  // register user
  public People registration(PeopleRequest peopleRequest) {
    People people = modelMapper.map(peopleRequest, People.class);
    User user = modelMapper.map(peopleRequest, User.class);

    // set password
    user.setPassword(passwordEncoder.encode(peopleRequest.getPassword()));

    // // set role to admin
    // List<Role> roles = new ArrayList<>();
    // roles.add(roleService.findByName("admin"));
    // user.setRoles(roles);

    // // set role to dosen
    // List<Role> roles = new ArrayList<>();
    // roles.add(roleService.findByName("dosen"));
    // user.setRoles(roles);

    // set role to user
    List<Role> roles = new ArrayList<>();
    roles.add(roleService.findByName("mahasiswa"));
    user.setRoles(roles);

    people.setUser(user);
    user.setPeople(people);

    return peopleRepository.save(people);
  }

  // login user
  public LoginResponse login(LoginRequest loginRequest) {
    // set principle
    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
      loginRequest.getUsername(),
      loginRequest.getPassword()
    );
    Authentication authentication = authenticationManager.authenticate(authReq);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // set login response
    User user = userRepository
      .findByUsernameOrPeopleEmail(
        loginRequest.getUsername(),
        loginRequest.getUsername()
      )
      .get();

    UserDetails userDetails = appUserDetailService.loadUserByUsername(
      loginRequest.getUsername()
    );

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setUsername(user.getUsername());
    loginResponse.setEmail(user.getPeople().getEmail());
    loginResponse.setAuthorities(
      userDetails
        .getAuthorities()
        .stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList())
    );

    return loginResponse;
  }
}

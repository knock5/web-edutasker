package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!")
      );
  }

  public User create(User user) {
    return userRepository.save(user);
  }

  public User update(Integer id, User user) {
    getById(id);
    user.setId(id);
    return userRepository.save(user);
  }

  public User delete(Integer id) {
    User user = getById(id);
    userRepository.delete(user);
    return user;
  }

  public Integer findUserIdByUsername(String username) {
    // Optional<User> userOptional = userRepository.findByUsername(username);

    // return userOptional
    //   .map(User::getId)
    //   .orElseThrow(() ->
    //     new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!")
    //   );
    Optional<User> userOptional = userRepository.findByUsername(username);

    if (userOptional.isPresent()) {
      User user = userOptional.get();
      return user.getId();
    } else {
      System.out.println("User not found for username: " + username);
      return null;
    }
  }
}

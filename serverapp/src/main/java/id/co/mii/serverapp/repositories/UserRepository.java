package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  public boolean existsByUsername(String username);

  // login with username or email
  public Optional<User> findByUsernameOrPeopleEmail(
    String username,
    String email
  );

  // find by username
  public Optional<User> findByUsername(String username);
}

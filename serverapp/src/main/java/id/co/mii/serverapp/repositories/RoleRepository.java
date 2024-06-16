package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  public Role findByName(String name);
}

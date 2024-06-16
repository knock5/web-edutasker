package id.co.mii.clientapp.controllers.rest;

import id.co.mii.clientapp.models.Role;
import id.co.mii.clientapp.services.RoleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/role")
public class RestRoleController {

  private RoleService roleService;

  @GetMapping
  public List<Role> getAll() {
    return roleService.getAll();
  }

  @PostMapping
  public Role create(@RequestBody Role role) {
    return roleService.create(role);
  }

  @GetMapping("/{id}")
  public Role getById(@PathVariable Integer id) {
    return roleService.getById(id);
  }

  @PutMapping("update/{id}")
  public Role update(@PathVariable Integer id, @RequestBody Role role) {
    return roleService.update(id, role);
  }

  @DeleteMapping("/{id}")
  public Role delete(@PathVariable Integer id) {
    return roleService.delete(id);
  }
}

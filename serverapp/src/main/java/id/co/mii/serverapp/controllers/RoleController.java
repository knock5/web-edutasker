package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.services.RoleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
@PreAuthorize("hasAnyRole('ADMIN', 'DOSEN', 'MAHASISWA')")
public class RoleController {

  private RoleService roleService;

  @GetMapping
  public List<Role> getAll() {
    return roleService.getAll();
  }

  @GetMapping("/{id}")
  public Role getById(@PathVariable Integer id) {
    return roleService.getById(id);
  }

  @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
  @PostMapping
  public Role create(@RequestBody Role role) {
    return roleService.create(role);
  }

  @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
  @PutMapping("/{id}")
  public Role update(@PathVariable Integer id, @RequestBody Role role) {
    return roleService.update(id, role);
  }

  @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
  @DeleteMapping("/{id}")
  public Role delete(@PathVariable Integer id) {
    return roleService.delete(id);
  }

  // find by name
  @GetMapping("/find")
  public Role findByName(@RequestParam(name = "name") String name) {
    return roleService.findByName(name);
  }
}

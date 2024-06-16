package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.People;
import id.co.mii.serverapp.services.PeopleService;
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
@RequestMapping("/people")
@PreAuthorize("hasAnyRole('ADMIN', 'DOSEN', 'MAHASISWA')")
public class PeopleController {

  private PeopleService peopleService;

  @GetMapping
  public List<People> getAll() {
    return peopleService.getAll();
  }

  @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_EDITOR_OFFICER')")
  @PostMapping
  public People create(@RequestBody People People) {
    return peopleService.create(People);
  }

  @GetMapping("/{id}")
  public People getById(@PathVariable Integer id) {
    return peopleService.getById(id);
  }

  @PutMapping("/{id}")
  public People update(@PathVariable Integer id, @RequestBody People People) {
    return peopleService.update(id, People);
  }

  @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_EDITOR_OFFICER')")
  @DeleteMapping("/{id}")
  public People delete(@PathVariable Integer id) {
    return peopleService.delete(id);
  }

  // get people by role name
  @GetMapping("/by-role/{roleName}")
  public List<People> getPeopleByRole(
    @PathVariable("roleName") String roleName
  ) {
    return peopleService.getPeopleByRole(roleName);
  }

  @GetMapping("/profile")
  public People getProfile(@RequestParam(name = "name") String username) {
    return peopleService.getProfileByName(username);
  }
}

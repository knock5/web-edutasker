package id.co.mii.clientapp.controllers.rest;

import id.co.mii.clientapp.models.People;
import id.co.mii.clientapp.services.PeopleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/people")
public class RestPeopleController {

  private PeopleService peopleService;

  @GetMapping
  public List<People> getAll() {
    return peopleService.getAll();
  }

  @GetMapping("/{id}")
  public People getById(@PathVariable Integer id) {
    return peopleService.getById(id);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<People> update(
    @PathVariable Integer id,
    @RequestBody People people
  ) {
    People updatedPeople = peopleService.update(id, people);
    return ResponseEntity.ok(updatedPeople);
  }

  @DeleteMapping("/{id}")
  public People delete(@PathVariable Integer id) {
    return peopleService.delete(id);
  }

  @GetMapping("/by-role/{roleName}")
  public List<People> getPeopleByRoleName(
    @PathVariable("roleName") String roleName
  ) {
    return peopleService.getPeopleByRoleName(roleName);
  }

  @GetMapping("/profile")
  public People getProfileByName(Authentication auth) {
    return peopleService.getProfileByName(auth.getName());
  }
}

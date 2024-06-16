package id.co.mii.clientapp.controllers.rest;

import id.co.mii.clientapp.models.Status;
import id.co.mii.clientapp.services.StatusService;
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
@RequestMapping("/api/status")
public class RestStatusController {

  private StatusService statusService;

  @GetMapping
  public List<Status> getAll() {
    return statusService.getAll();
  }

  @GetMapping("/{id}")
  public Status getById(@PathVariable Integer id) {
    return statusService.getById(id);
  }

  @PostMapping
  public Status create(@RequestBody Status status) {
    return statusService.create(status);
  }

  @PutMapping("/{id}")
  public Status update(@PathVariable Integer id, @RequestBody Status status) {
    return statusService.update(id, status);
  }

  @DeleteMapping("/{id}")
  public Status delete(@PathVariable Integer id) {
    return statusService.delete(id);
  }
}

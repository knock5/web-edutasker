package id.co.mii.clientapp.controllers.rest;

import id.co.mii.clientapp.models.TaskDosen;
import id.co.mii.clientapp.models.dto.request.TaskDosenRequest;
import id.co.mii.clientapp.services.TaskDosenService;
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
@RequestMapping("/api/taskdosen")
public class RestTaskDosenController {

  private TaskDosenService taskDosenService;

  @GetMapping
  public List<TaskDosen> getAll() {
    return taskDosenService.getAll();
  }

  @GetMapping("/active")
  public List<TaskDosen> getActiveTasks() {
    return taskDosenService.getActiveTasks();
  }

  @GetMapping("/people/{id}")
  public List<TaskDosen> getByPeopleId(@PathVariable Integer id) {
    return taskDosenService.getByPeopleId(id);
  }

  @GetMapping("/{id}")
  public TaskDosen getById(@PathVariable Integer id) {
    return taskDosenService.getById(id);
  }

  @PostMapping
  public TaskDosen create(@RequestBody TaskDosenRequest taskDosenRequest) {
    return taskDosenService.create(taskDosenRequest);
  }

  @PutMapping("/{id}")
  public TaskDosen update(
    @PathVariable Integer id,
    @RequestBody TaskDosenRequest taskDosenRequest
  ) {
    return taskDosenService.update(id, taskDosenRequest);
  }

  @DeleteMapping("/{id}")
  public TaskDosen delete(@PathVariable Integer id) {
    return taskDosenService.delete(id);
  }
}

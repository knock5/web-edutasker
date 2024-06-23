package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.TaskDosen;
import id.co.mii.serverapp.models.dto.request.TaskDosenRequest;
import id.co.mii.serverapp.services.TaskDosenService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/taskdosen")
@PreAuthorize("hasAnyRole('ADMIN', 'DOSEN', 'MAHASISWA')")
public class TaskDosenController {

  private TaskDosenService taskDosenService;

  @GetMapping
  public List<TaskDosen> getAll() {
    return taskDosenService.getAll();
  }

  @GetMapping("/{id}")
  public TaskDosen getById(@PathVariable Integer id) {
    return taskDosenService.getById(id);
  }

  @PostMapping
  public TaskDosen createDTO(@RequestBody TaskDosenRequest taskDosenRequest) {
    return taskDosenService.createDTO(taskDosenRequest);
  }

  @PutMapping("/{id}")
  public TaskDosen update(
    @PathVariable Integer id,
    @RequestBody TaskDosenRequest taskDosenRequest
  ) {
    return taskDosenService.updateDTO(id, taskDosenRequest);
  }

  @DeleteMapping("/{id}")
  public TaskDosen delete(@PathVariable Integer id) {
    return taskDosenService.delete(id);
  }

  @GetMapping("/active")
  public List<TaskDosen> getActiveTasks() {
    return taskDosenService.getActiveTasks();
  }

  @GetMapping("/people/{id}")
  public List<TaskDosen> getByPeopleId(@PathVariable Integer id) {
    return taskDosenService.getByPeopleId(id);
  }
}

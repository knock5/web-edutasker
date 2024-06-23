package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.TaskDosen;
import id.co.mii.serverapp.models.dto.request.TaskDosenRequest;
import id.co.mii.serverapp.repositories.TaskDosenRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class TaskDosenService {

  private TaskDosenRepository taskDosenRepository;
  private PeopleService peopleService;
  private ModelMapper modelMapper;

  public List<TaskDosen> getAll() {
    return taskDosenRepository.findAll();
  }

  public TaskDosen createDTO(TaskDosenRequest taskDosenRequest) {
    TaskDosen taskDosen = modelMapper.map(taskDosenRequest, TaskDosen.class);

    taskDosen.setPeople(peopleService.getById(taskDosenRequest.getPeopleId()));

    return taskDosenRepository.save(taskDosen);
  }

  public TaskDosen getById(Integer id) {
    return taskDosenRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Kososng")
      );
  }

  public TaskDosen updateDTO(Integer id, TaskDosenRequest taskDosenRequest) {
    TaskDosen existingTaskDosen = getById(id);

    modelMapper.map(taskDosenRequest, existingTaskDosen);

    return taskDosenRepository.save(existingTaskDosen);
  }

  public TaskDosen delete(Integer id) {
    TaskDosen taskDosen = getById(id);
    taskDosenRepository.delete(taskDosen);

    return taskDosen;
  }

  public List<TaskDosen> getActiveTasks() {
    return taskDosenRepository.findByIsActiveTrue();
  }

  public List<TaskDosen> getByPeopleId(Integer peopleId) {
    return taskDosenRepository.findByPeopleId(peopleId);
  }
}

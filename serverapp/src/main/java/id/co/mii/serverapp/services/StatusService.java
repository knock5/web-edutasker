package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Status;
import id.co.mii.serverapp.repositories.StatusRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class StatusService {

  private StatusRepository statusRepository;

  public List<Status> getAll() {
    return statusRepository.findAll();
  }

  public Status getById(Integer id) {
    return statusRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found!!")
      );
  }

  public Status create(Status status) {
    return statusRepository.save(status);
  }

  public Status update(Integer id, Status status) {
    getById(id);
    status.setId(id);
    return statusRepository.save(status);
  }

  public Status delete(Integer id) {
    Status status = getById(id);
    statusRepository.delete(status);
    return status;
  }

  // find by name
  public Status findByName(String name) {
    return statusRepository.findByName(name);
  }
}

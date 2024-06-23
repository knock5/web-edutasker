package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.TaskDosen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDosenRepository extends JpaRepository<TaskDosen, Integer> {
  // get all tasks by dosen id
  public List<TaskDosen> findByPeopleId(Integer id);

  // get all active tasks
  List<TaskDosen> findByIsActiveTrue();
}

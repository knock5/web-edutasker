package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.History;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
  // get history by user id
  public List<History> findByComplaintPeopleId(Integer id);
}

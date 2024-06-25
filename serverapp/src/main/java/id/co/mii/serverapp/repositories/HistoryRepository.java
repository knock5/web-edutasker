package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.History;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
  @Query(
    "SELECT h FROM History h " +
    "JOIN h.complaint c " +
    "JOIN c.taskDosen td " +
    "WHERE td.people.id = :peopleId"
  )
  List<History> findByTaskDosenPeopleId(@Param("peopleId") Integer peopleId);

  @Query(
    "SELECT h FROM History h " +
    "JOIN h.complaint c " +
    "WHERE c.people.id = :peopleId"
  )
  List<History> findByPeopleId(@Param("peopleId") Integer peopleId);
}

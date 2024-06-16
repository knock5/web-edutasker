package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Complaint;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
  @Query(
    "SELECT c FROM Complaint c JOIN c.status s WHERE s.name <> 'Selesai' OR s.name <> 'selesai'"
  )
  public List<Complaint> findAllByStatusNameNotSelesai();

  // get all complaint by user id
  public List<Complaint> findByPeopleId(Integer id);
}

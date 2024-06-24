package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.FollowUp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Integer> {
  List<FollowUp> findByUserId(Integer id);
}

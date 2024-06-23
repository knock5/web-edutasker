package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Complaint;
import id.co.mii.serverapp.models.dto.request.ComplaintRequest;
import id.co.mii.serverapp.services.ComplaintService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/complaint")
@PreAuthorize("hasAnyRole('ADMIN', 'DOSEN', 'MAHASISWA')")
public class ComplaintController {

  private ComplaintService complaintService;

  @GetMapping
  public List<Complaint> getAll() {
    return complaintService.getAll();
  }

  @GetMapping("/{id}")
  public Complaint getById(@PathVariable Integer id) {
    return complaintService.getById(id);
  }

  @PostMapping
  public Complaint createDTO(@RequestBody ComplaintRequest complaintRequest) {
    return complaintService.createDTO(complaintRequest);
  }

  @DeleteMapping("/{id}")
  public Complaint delete(@PathVariable Integer id) {
    return complaintService.delete(id);
  }

  // get complaint not resolved
  @GetMapping("/active")
  public List<Complaint> findAllByStatusName() {
    return complaintService.findAllByStatusName();
  }

  // get all complaint by user id
  @GetMapping("/userId/{userId}")
  public List<Complaint> getComplaintByUserId(@PathVariable Integer userId) {
    return complaintService.getComplaintByUserId(userId);
  }
}

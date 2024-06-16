package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.FollowUp;
import id.co.mii.serverapp.models.dto.request.FollowUpRequest;
import id.co.mii.serverapp.services.FollowUpService;
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
@RequestMapping("/followUp")
@PreAuthorize("hasAnyRole('ADMIN', 'DOSEN', 'MAHASISWA')")
public class FollowUpController {

  private FollowUpService followUpService;

  @GetMapping
  public List<FollowUp> getAll() {
    return followUpService.getAll();
  }

  @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_EDITOR_OFFICER')")
  @PostMapping
  public FollowUp createDTO(@RequestBody FollowUpRequest followUpRequest) {
    return followUpService.createDTO(followUpRequest);
  }

  @GetMapping("/{id}")
  public FollowUp getById(@PathVariable Integer id) {
    return followUpService.getById(id);
  }

  @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_EDITOR_OFFICER')")
  @PutMapping("/{id}")
  public FollowUp update(
    @PathVariable Integer id,
    @RequestBody FollowUp FollowUp
  ) {
    return followUpService.update(id, FollowUp);
  }

  @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_EDITOR_OFFICER')")
  @DeleteMapping("/{id}")
  public FollowUp delete(@PathVariable Integer id) {
    return followUpService.delete(id);
  }
}

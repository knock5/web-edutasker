package id.co.mii.clientapp.controllers.rest;

import id.co.mii.clientapp.models.FollowUp;
import id.co.mii.clientapp.models.dto.request.FollowUpRequest;
import id.co.mii.clientapp.services.FollowUpService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/followUp")
public class RestFollowUpController {

  private FollowUpService followUpService;

  @GetMapping
  public List<FollowUp> getAll() {
    return followUpService.getAll();
  }

  @GetMapping("/{id}")
  public FollowUp getById(@PathVariable Integer id) {
    return followUpService.getById(id);
  }

  @PostMapping(consumes = { "application/json" })
  public FollowUp create(@RequestBody FollowUpRequest followUpRequest) {
    return followUpService.create(followUpRequest);
  }

  @DeleteMapping("/{id}")
  public FollowUp delete(@PathVariable Integer id) {
    return followUpService.delete(id);
  }
}

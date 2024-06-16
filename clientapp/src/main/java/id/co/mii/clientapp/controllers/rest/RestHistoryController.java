package id.co.mii.clientapp.controllers.rest;

import id.co.mii.clientapp.models.History;
import id.co.mii.clientapp.services.HistoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/history")
public class RestHistoryController {

  private HistoryService historyService;

  @GetMapping
  public List<History> getAll() {
    return historyService.getAll();
  }

  @GetMapping("/{id}")
  public History getById(@PathVariable Integer id) {
    return historyService.getById(id);
  }

  // get history by people id
  @GetMapping("/user/{id}")
  public List<History> getHistoryByPeopleId(@PathVariable Integer id) {
    return historyService.getHistoryByPeopleId(id);
  }
}

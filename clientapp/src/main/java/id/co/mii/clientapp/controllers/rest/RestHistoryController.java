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

  // get history by taskDosen people id
  @GetMapping("/byPeople/{id}")
  public List<History> getHistoryByPeopleId(@PathVariable Integer id) {
    return historyService.getHistoryByPeopleId(id);
  }

  // get history by MHS id
  @GetMapping("/byMhsId/{id}")
  public List<History> getHistoryByMhsId(@PathVariable Integer id) {
    return historyService.getHistoryByMhsId(id);
  }
}

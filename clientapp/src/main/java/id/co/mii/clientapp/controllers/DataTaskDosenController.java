package id.co.mii.clientapp.controllers;

import id.co.mii.clientapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DataTaskDosenController {

  @Autowired
  private UserService userService;

  @GetMapping("/task-dosen")
  public String TaskDosenPage(Model model) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();

    // get user id from username
    String username = auth.getName();
    Integer userId = userService.findUserIdByUsername(username);

    model.addAttribute("userId", userId);
    model.addAttribute(
      "username",
      auth.getName().substring(0, 1).toUpperCase() + auth.getName().substring(1)
    );
    model.addAttribute("title", "EduTasker - Tugas Dosen");
    model.addAttribute("isActive", "task-dosen");

    return "views/officer/data-tasks";
  }

  @GetMapping("/add-taskdosen-page")
  public String createTaskDosenPage(Model model) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();

    // get user id from username
    String username = auth.getName();
    Integer userId = userService.findUserIdByUsername(username);

    model.addAttribute("userId", userId);
    model.addAttribute(
      "username",
      auth.getName().substring(0, 1).toUpperCase() + auth.getName().substring(1)
    );
    model.addAttribute("title", "EduTasker - Tugas Dosen");
    model.addAttribute("isActive", "task-dosen");

    return "views/officer/add-task";
  }

  @GetMapping("/edit-taskdosen-page/{id}")
  public String editTaskDosenPage(@PathVariable Integer id, Model model) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();

    // get user id from username
    String username = auth.getName();
    Integer userId = userService.findUserIdByUsername(username);

    model.addAttribute("userId", userId);
    model.addAttribute("taskId", id);
    model.addAttribute(
      "username",
      auth.getName().substring(0, 1).toUpperCase() + auth.getName().substring(1)
    );
    model.addAttribute("title", "EduTasker - Edit Tugas Dosen");
    model.addAttribute("isActive", "task-dosen");

    return "views/officer/edit-task";
  }
}

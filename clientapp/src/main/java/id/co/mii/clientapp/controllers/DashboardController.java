package id.co.mii.clientapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

  @GetMapping("/a-dashboard")
  public String dashboardAdmin(Model model) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    model.addAttribute(
      "username",
      auth.getName().substring(0, 1).toUpperCase() + auth.getName().substring(1)
    );

    model.addAttribute("title", "LaporYuk - Dashboard Admin");
    model.addAttribute("isActive", "a-dashboard");

    return "views/admin/dashboard-admin";
  }

  @GetMapping("/u-dashboard")
  public String dashboardUser(Model model) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    model.addAttribute(
      "username",
      auth.getName().substring(0, 1).toUpperCase() + auth.getName().substring(1)
    );

    model.addAttribute("title", "LaporYuk - Dashboard User");
    model.addAttribute("isActive", "u-dashboard");

    return "views/user/dashboard-user";
  }

  @GetMapping("/o-dashboard")
  public String dashboardOfficer(Model model) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    model.addAttribute(
      "username",
      auth.getName().substring(0, 1).toUpperCase() + auth.getName().substring(1)
    );

    model.addAttribute("title", "LaporYuk - Dashboard Officer");
    model.addAttribute("isActive", "o-dashboard");

    return "views/officer/dashboard-officer";
  }
}

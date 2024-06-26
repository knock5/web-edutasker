package id.co.mii.clientapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping
  public String index(Model model) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    model.addAttribute(
      "username",
      auth.getName().substring(0, 1).toUpperCase() + auth.getName().substring(1)
    );
    model.addAttribute("title", "EduTasker - Home");
    model.addAttribute("isActive", "home");

    return "views/home";
  }

  @GetMapping("/home")
  public String home(Model model) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    model.addAttribute(
      "username",
      auth.getName().substring(0, 1).toUpperCase() + auth.getName().substring(1)
    );
    model.addAttribute("title", "EduTasker - Home");
    model.addAttribute("isActive", "home");

    return "views/home";
  }

  @GetMapping("/h-tentang")
  public String tentang(Model model) {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();
    model.addAttribute(
      "username",
      auth.getName().substring(0, 1).toUpperCase() + auth.getName().substring(1)
    );

    model.addAttribute("title", "EduTasker - Tentang");
    model.addAttribute("isActive", "h-tentang");

    return "views/tentang";
  }
}

package id.co.mii.clientapp.controllers;

import id.co.mii.clientapp.models.dto.request.LoginRequest;
import id.co.mii.clientapp.models.dto.request.PeopleRequest;
import id.co.mii.clientapp.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping
public class AuthController {

  private AuthService authService;

  @GetMapping("/signup")
  public String regisPage(PeopleRequest peopleRequest, Model model) {
    model.addAttribute("title", "LaporYuk - Sign Up");

    return "views/auth/registration-page";
  }

  @GetMapping("/signin")
  public String loginPage(LoginRequest loginRequest, Model model) {
    model.addAttribute("title", "LaporYuk - Sign In");

    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();

    if (auth instanceof AnonymousAuthenticationToken) {
      return "views/auth/login-page";
    }

    return "redirect:/home";
  }

  @PostMapping("/login")
  public String login(LoginRequest loginRequest) {
    if (!authService.login(loginRequest)) {
      return "redirect:/signin?error=true";
    }

    return "redirect:/home";
  }

  @PostMapping("/registration")
  public String registerUser(
    PeopleRequest peopleRequest,
    RedirectAttributes ra
  ) {
    ra.addFlashAttribute("message", "Registrasi berhasil, silahkan login!");
    authService.registerUser(peopleRequest);

    return "redirect:/signin";
  }
}

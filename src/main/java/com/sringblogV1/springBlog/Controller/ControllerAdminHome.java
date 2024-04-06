package com.sringblogv1.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerAdminHome {
  @GetMapping("/admin")
  public String home(Model model){
    model.addAttribute("currentPage", "home");
    return "admin/home";
  }
  @GetMapping("/users")
  public String users(Model model){
    model.addAttribute("currentPage", "users");
    return "admin/users";
  }
}

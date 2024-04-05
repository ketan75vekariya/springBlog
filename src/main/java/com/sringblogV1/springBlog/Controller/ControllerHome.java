package com.sringblogV1.springBlog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ControllerHome {
 
 
 @GetMapping("/home")
  public String home(Model model){
    return "home";
  }
}

package com.sringblogV1.springBlog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControllerHome {
 
  
  // Controller method for the isActivePage logic (not mapped to any HTTP endpoint)
  

 @GetMapping("/home")
  public String home(Model model){
    model.addAttribute("currentPage", "home");
    return "home";
  }
  @GetMapping("/about")
  public String about(Model model){
    model.addAttribute("currentPage", "about");
    return "about";
  }
  @GetMapping("/contact")
  public String contact(Model model){
    model.addAttribute("currentPage", "contact");
    return "contact";
  }
  @GetMapping("/blogs")
  public String blogs(Model model){
    model.addAttribute("currentPage", "blog");
    return "blogs";
  }
  @GetMapping("/blog-post")
  public String blogPost(Model model){
    model.addAttribute("currentPage", "blog");
    return "blog_post";
  }
}

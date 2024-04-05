package com.sringblogv1.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControllerHome {
 
  
  // Controller method for the isActivePage logic (not mapped to any HTTP endpoint)
  

 @GetMapping("/home")
  public String home(Model model){
    model.addAttribute("currentPage", "home");
    return "view/home";
  }
  @GetMapping("/about")
  public String about(Model model){
    model.addAttribute("currentPage", "about");
    return "view/about";
  }
  @GetMapping("/contact")
  public String contact(Model model){
    model.addAttribute("currentPage", "contact");
    return "view/contact";
  }
  @GetMapping("/blogs")
  public String blogs(Model model){
    model.addAttribute("currentPage", "blog");
    return "view/blogs";
  }
  @GetMapping("/blog-post")
  public String blogPost(Model model){
    model.addAttribute("currentPage", "blog");
    return "view/blog_post";
  }
}

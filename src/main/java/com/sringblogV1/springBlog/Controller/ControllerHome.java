package com.sringblogv1.springblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sringblogv1.springblog.model.Category;
import com.sringblogv1.springblog.model.Post;
import com.sringblogv1.springblog.services.CategoryService;
import com.sringblogv1.springblog.services.PostService;


@Controller
public class ControllerHome {
 
  @Autowired
  private PostService postService;
  
  @Autowired
  private CategoryService categoryService;
  // Controller method for the isActivePage logic (not mapped to any HTTP endpoint)
  

 @GetMapping("/")
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
    List<Post> posts = postService.getAll();
    // Check if there are any posts
    if (!posts.isEmpty()) {
      // Retrieve the first post
      Post firstPost = posts.get(0);
      
      // Add the first post to the model
      model.addAttribute("firstPost", firstPost);
  }
    model.addAttribute("posts", posts);
    
    List<Category> categories = categoryService.getAll();
    model.addAttribute("categories", categories);

    return "view/blogs";
  }
  @GetMapping("/blog-post")
  public String blogPost(Model model){
    model.addAttribute("currentPage", "blog");
    return "view/blog_post";
  }
}

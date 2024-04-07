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
public class ControllerBolg {

  @Autowired
  private PostService postService;

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/adminblogs")
  public String blogs(Model model){
    model.addAttribute("currentPage", "blog");
    List<Post> posts = postService.getAll();
    
    model.addAttribute("posts", posts);
    return "admin/blogs";
  }
  @GetMapping("/addblog")
  public String addBlog(Model model){
    model.addAttribute("currentPage", "blog");
    List<Category> categories = categoryService.getAll();
    model.addAttribute("categories", categories);
    return "admin/addblog";
  }
  
}

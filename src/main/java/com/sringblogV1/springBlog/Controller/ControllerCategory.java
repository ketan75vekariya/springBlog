package com.sringblogv1.springblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sringblogv1.springblog.model.Category;
import com.sringblogv1.springblog.services.CategoryService;

@Controller
public class ControllerCategory {
  @Autowired
  private CategoryService categoryService;

  @GetMapping("/categories")
  public String categories(Model model){
    model.addAttribute("currentPage", "register");
    List<Category> categories = categoryService.getAll();
    model.addAttribute("categories", categories);
    return "admin/categories";
  }
  @GetMapping("/addcategories")
  public String addcategories(Model model){
    model.addAttribute("currentPage", "register");
    return "admin/addcategories";
  }
}

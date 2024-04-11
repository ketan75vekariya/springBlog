package com.sringblogv1.springblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sringblogv1.springblog.model.Category;
import com.sringblogv1.springblog.services.CategoryService;

@Controller
public class ControllerCategory {
  @Autowired
  private CategoryService categoryService;

  @GetMapping("/categories")
  public String categories(Model model) {
    model.addAttribute("currentPage", "register");
    List<Category> categories = categoryService.getAll();
    model.addAttribute("categories", categories);
    return "admin/categories";
  }

  @GetMapping("/addcategories")
  public String addcategories(Model model) {
    model.addAttribute("currentPage", "register");
    Category category = new Category();
    model.addAttribute("category", category);
    return "admin/addcategories";

  }

  @PostMapping("/addcategories")
  public String postcategories(@ModelAttribute Category category) {
    categoryService.save(category);
    return "redirect:/categories";

  }

  @GetMapping("/category/{id}/edit")
  public String getCategoryForEdit(@PathVariable Long id, Model model) {
    Optional<Category> optionalCategory = categoryService.getById(id);
    if (optionalCategory.isPresent()) {
      Category category = new Category();
      model.addAttribute("category", category);
      return "admin/editcategories";
    } else {
      return "/404";
    }

  }

  @PostMapping("/category/{id}/edit")
  public String updateCategory(@PathVariable Long id, @ModelAttribute Category category) {
    Optional<Category> optionalCategory = categoryService.getById(id);
    if (optionalCategory.isPresent()) {
      Category existingCategory = optionalCategory.get();
      existingCategory.setCategoryName(category.getCategoryName());
      categoryService.save(existingCategory);
    }
    return "redirect:/categories";

  }

  @GetMapping("/category/{id}/delete")
  public String deleteCategory(@PathVariable Long id) {
    Optional<Category> optionalCategory = categoryService.getById(id);
    if (optionalCategory.isPresent()) {
      Category category = optionalCategory.get();
      categoryService.delete(category);
      return "redirect:/categories";
    }
    return "redirect:/categories?error";
  }

}

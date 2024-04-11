package com.sringblogv1.springblog.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sringblogv1.springblog.model.Category;
import com.sringblogv1.springblog.repositories.CategoryRepository;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  public Category save(Category category) {
    if (category.getId() == 0) {
      category.setCreatedAt(LocalDateTime.now());
    }
    return categoryRepository.save(category);
  }

  public Optional<Category> getById(Long id) {
    return categoryRepository.findById(id);
  }

  public void delete(Category category) {
    categoryRepository.delete(category);
  }
}

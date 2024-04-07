package com.sringblogv1.springblog.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sringblogv1.springblog.model.Account;
import com.sringblogv1.springblog.model.Category;
import com.sringblogv1.springblog.model.Post;
import com.sringblogv1.springblog.services.AccountService;
import com.sringblogv1.springblog.services.CategoryService;
import com.sringblogv1.springblog.services.PostService;
@Component
public class SeedData implements CommandLineRunner {
  
  @Autowired
  private PostService postService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private CategoryService categoryService;
  

  @Override
  public void run(String... args) throws Exception {

    Account account01 = new Account();
    Account account02 = new Account();

    account01.setEmail("abc@xyz.com");
    account01.setFirstname("Userkumar");
    account01.setLastname("Kapoor");
    account01.setPassword("User@123");


    account02.setEmail("xyz@xyz.com");
    account02.setFirstname("Adminkumar");
    account02.setLastname("Mathur");
    account02.setPassword("Admin@123");

    accountService.save(account01);
    accountService.save(account02);

    Category category01 = new Category();
    Category category02 = new Category();

    category01.setCategory("JavaScript");
    category02.setCategory("Spring Boot");

    categoryService.save(category01);
    categoryService.save(category02);


    List<Post> posts= postService.getAll();
    if(posts.size() == 0){
      Post post01 = new Post();
      post01.setTitle("Post 01 Lorem ipsum dolor sit amet consectetur adipisicing elit.");
      post01.setBody("Post01 body Expedita doloremque praesentium corrupti harum ipsum commodi. Id est maxime a magni nulla eligendi odit possimus, eos saepe tenetur explicabo eveniet eius?...........................");
      post01.setAccount(account01);
      postService.save(post01);

      Post post02 = new Post();
      post02.setTitle("Post02 Expedita doloremque praesentium corrupti harum ipsum");
      post02.setBody("Post01 body Expedita doloremque praesentium corrupti harum ipsum commodi. Id est maxime a magni nulla eligendi odit possimus, eos saepe tenetur explicabo eveniet eius?......................................................");
      post02.setAccount(account02);
      postService.save(post02);
    }
  }
  
}

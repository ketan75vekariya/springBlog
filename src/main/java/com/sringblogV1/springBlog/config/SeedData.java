
package com.sringblogv1.springblog.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.sringblogv1.springblog.model.Account;
import com.sringblogv1.springblog.model.Authority;
import com.sringblogv1.springblog.model.Category;
import com.sringblogv1.springblog.model.Post;
import com.sringblogv1.springblog.services.AccountService;
import com.sringblogv1.springblog.services.AuthorityService;
import com.sringblogv1.springblog.services.CategoryService;
import com.sringblogv1.springblog.services.PostService;
import com.sringblogv1.springblog.util.constant.Privillage;
import com.sringblogv1.springblog.util.constant.Roles;

@Component
public class SeedData implements CommandLineRunner {

  @Autowired
  private PostService postService;
  @Autowired
  private AccountService accountService;
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private AuthorityService authorityService;

  @Override
  public void run(String... args) throws Exception {

    for (Privillage auth : Privillage.values()) {
      Authority authority = new Authority();
      authority.setId(auth.getPrivillageId());
      authority.setName(auth.getPrivillage());
      authorityService.save(authority);
    }

    Account account01 = new Account();
    Account account02 = new Account();
    Account account03 = new Account();
    Account account04 = new Account();

    account01.setEmail("abc@xyz.com");
    account01.setFirstname("Userkumar");
    account01.setLastname("Kapoor");
    account01.setPassword("User@123");
    account01.setAge(35);
    account01.setDate_of_birth(LocalDate.parse("1988-07-12"));
    account01.setGender("male");

    account02.setEmail("xyz@xyz.com");
    account02.setFirstname("Adminkumar");
    account02.setLastname("Mathur");
    account02.setPassword("User@123");
    account02.setRole(Roles.EDITOR.getRole());
    account02.setAge(30);
    account02.setDate_of_birth(LocalDate.parse("1996-07-12"));
    account02.setGender("male");

    account03.setEmail("xyz@abc.com");
    account03.setFirstname("Lavanasur");
    account03.setLastname("Asur");
    account03.setPassword("User@123");
    account03.setRole(Roles.ADMIN.getRole());
    account03.setAge(32);
    account03.setDate_of_birth(LocalDate.parse("1990-09-23"));
    account03.setGender("male");

    account04.setEmail("abc@abc.com");
    account04.setFirstname("Adminkumar");
    account04.setLastname("Mathur");
    account04.setPassword("User@123");
    account04.setRole(Roles.EDITOR.getRole());
    Set<Authority> authorities = new HashSet<>();
    authorityService.findById(Privillage.RESET_ANY_USER_PASSWORD.getPrivillageId()).ifPresent(authorities::add);
    authorityService.findById(Privillage.ACCESS_ADMIN_PANEL.getPrivillageId()).ifPresent(authorities::add);
    account04.setAuthorities(authorities);
    account04.setAge(28);
    account04.setDate_of_birth(LocalDate.parse("1988-07-12"));
    account04.setGender("male");

    accountService.save(account01);
    accountService.save(account02);
    accountService.save(account03);
    accountService.save(account04);

    Category category01 = new Category();
    Category category02 = new Category();

    category01.setCategoryName("JavaScript");
    category02.setCategoryName("Spring Boot");

    categoryService.save(category01);
    categoryService.save(category02);

    List<Post> posts = postService.getAll();
    if (posts.size() == 0) {
      Post post01 = new Post();
      post01.setTitle("Post 01 Lorem ipsum dolor sit amet consectetur adipisicing elit.");
      post01.setBody(
          "Post01 body Expedita doloremque praesentium corrupti harum ipsum commodi. Id est maxime a agni nulla eligendi odit possimus, eos saepe tenetur explicabo eveniet ius?...........................");
      post01.setAccount(account01);
      postService.save(post01);

      Post post02 = new Post();
      post02.setTitle("Post02 Expedita doloremque praesentium corrupti harum ipsum");
      post02.setBody(
          "Post01 body Expedita doloremque praesentium corrupti harum ipsum commodi. Id est maxime a agni nulla eligendi odit possimus, eos saepe tenetur explicabo eveniet ius?......................................................");
      post02.setAccount(account02);
      postService.save(post02);
    }
  }
}

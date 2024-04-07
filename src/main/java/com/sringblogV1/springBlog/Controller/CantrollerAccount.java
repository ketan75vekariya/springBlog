package com.sringblogv1.springblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sringblogv1.springblog.model.Account;
import com.sringblogv1.springblog.services.AccountService;

@Controller
public class CantrollerAccount {
  @Autowired
  private AccountService accountService;

  @GetMapping("/adminusers")
  public String users(Model model){
    model.addAttribute("currentPage", "users");
    List<Account> accounts = accountService.getAll();
    if (!accounts.isEmpty()) {
      // Retrieve the first post
      Account firstAccount = accounts.get(0);
      
      // Add the first post to the model
      model.addAttribute("firstAccount", firstAccount);
  }
    model.addAttribute("accounts", accounts);
    return "admin/users";
  }

  @GetMapping("/adminadduser")
  public String register(Model model){
    model.addAttribute("currentPage", "register");
    return "admin/register";
  }
}

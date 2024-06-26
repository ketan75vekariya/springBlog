package com.sringblogv1.springblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sringblogv1.springblog.model.Account;
import com.sringblogv1.springblog.services.AccountService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CantrollerAccount {
  @Autowired
  private AccountService accountService;

  @GetMapping("/adminusers")
  public String users(Model model) {
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
  public String register(Model model) {
    model.addAttribute("currentPage", "register");
    Account account = new Account();
    model.addAttribute("account", account);
    return "admin/register";
  }

  @PostMapping("/adminadduser")
  public String postMethodName(@Valid @ModelAttribute Account account, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/register";
    }
    accountService.save(account);

    return "admin/register";
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("currentPage", "register");
    return "admin/login";
  }

}

package com.sringblogv1.springblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sringblogv1.springblog.model.Account;

@Service
public class AccountService {
  @Autowired
  private AccountService accountService;

  public Account save(Account account){
    return accountService.save(account);
  }
}

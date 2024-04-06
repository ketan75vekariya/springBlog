package com.sringblogv1.springblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sringblogv1.springblog.model.Account;
import com.sringblogv1.springblog.repositories.AccountRepository;

@Service
public class AccountService {
  @Autowired
  private AccountRepository accountRepository;
  
  public Account save(Account account){
    return accountRepository.save(account);
  }
}

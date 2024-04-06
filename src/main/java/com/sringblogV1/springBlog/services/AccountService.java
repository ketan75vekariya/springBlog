package com.sringblogv1.springblog.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sringblogv1.springblog.model.Account;
import com.sringblogv1.springblog.repositories.AccountRepository;

@Service
public class AccountService {
  @Autowired
  private AccountRepository accountRepository;
  
  public List<Account> getAll(){
    return accountRepository.findAll();
  }


  public Account save(Account account){
    if(account.getId() == 0){
      account.setCreatedAt(LocalDateTime.now());
    }
    return accountRepository.save(account);
  }
}

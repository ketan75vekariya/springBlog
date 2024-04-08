package com.sringblogv1.springblog.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sringblogv1.springblog.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

  Optional<Account> findOneByEmailIgnoreCase(String email);

  
}
  


package com.sringblogv1.springblog.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sringblogv1.springblog.model.Account;
import com.sringblogv1.springblog.model.Authority;
import com.sringblogv1.springblog.repositories.AccountRepository;
import com.sringblogv1.springblog.util.constant.Roles;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountService implements UserDetailsService {

  // @Value("${spring.mvc.static-path-pattern}")
  private String photo_prefix = "/resources/static/**";
  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<Account> getAll() {
    return accountRepository.findAll();
  }

  public Account save(Account account) {
    if (account.getId() == 0) {
      account.setCreatedAt(LocalDateTime.now());
    }
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    if (account.getRole() == null) {
      account.setRole(Roles.USER.getRole());
    }

    if (account.getPhoto() == null) {
      String path = photo_prefix.replace("**",
          "admin/assets/img/avatar.png");
      account.setPhoto(path);
    }

    return accountRepository.save(account);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Account> optionalAccount = accountRepository.findOneByEmailIgnoreCase(email);
    if (!optionalAccount.isPresent()) {
      throw new UsernameNotFoundException("Account not found");
    }
    Account account = optionalAccount.get();
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole()));

    for (Authority _auth : account.getAuthorities()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(_auth.getName()));
    }

    return new User(account.getEmail(), account.getPassword(), grantedAuthorities);
  }

  public Optional<Account> findOneByEmail(String email) {
    return accountRepository.findOneByEmailIgnoreCase(email);
  }
}

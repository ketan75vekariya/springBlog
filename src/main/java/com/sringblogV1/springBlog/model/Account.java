package com.sringblogv1.springblog.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Email(message = "Invalid Email")
  @NotEmpty(message = "Email Missing")
  private String email;

  @NotEmpty(message = "Password Missing")
  private String password;

  private String firstname;

  private String lastname;

  private String role;

  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "account")
  private List<Post> posts;

  @ManyToMany
  @JoinTable(name = "account_authority", joinColumns = {
      @JoinColumn(name = "account_id", referencedColumnName = "id") }, inverseJoinColumns = {
          @JoinColumn(name = "authority_id", referencedColumnName = "id") })
  private Set<Authority> authorities = new HashSet<>();

}

package com.sringblogv1.springblog.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue
  private long id;

  private String title;
  
  @Column(columnDefinition = "TEXT")
  private String body;

  private LocalDateTime createdAt;
}

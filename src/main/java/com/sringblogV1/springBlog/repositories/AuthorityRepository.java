package com.sringblogv1.springblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sringblogv1.springblog.model.Authority;
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
  
}
  


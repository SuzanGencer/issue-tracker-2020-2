package com.kodstar.issuetracker.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ApplicationUserDAO extends CrudRepository<ApplicationUser, Long> {
     ApplicationUser findByUsername(String username);
}
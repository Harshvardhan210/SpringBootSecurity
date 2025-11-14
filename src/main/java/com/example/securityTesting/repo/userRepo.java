package com.example.securityTesting.repo;

import com.example.securityTesting.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface userRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}

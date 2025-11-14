package com.example.securityTesting.service;

import com.example.securityTesting.model.UserPrincipal;
import com.example.securityTesting.model.Users;
import com.example.securityTesting.repo.userRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepo.findByUsername(username);

        if(user==null){
            System.out.println("user not Found");
            throw new UsernameNotFoundException("user not Found");
        }

        return new UserPrincipal(user);
    }
}

package com.example.securityTesting.controller;

import com.example.securityTesting.model.student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<student> students = new ArrayList<>(List.of(
            new student(1,"Harsh",60),
            new student(2,"Ali",70)
    ));



    @GetMapping("/students")
    public List<student> getstudent(){
     return students;
    }

    @PostMapping("/students")
    public student addStudent(@RequestBody student nesStudent){
        students.add(nesStudent);
        return nesStudent;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getcsrftoken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}



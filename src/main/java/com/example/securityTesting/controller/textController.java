package com.example.securityTesting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class textController {

    @GetMapping("/")
    public String MassageTest(){
        return "Test working";
    }
}

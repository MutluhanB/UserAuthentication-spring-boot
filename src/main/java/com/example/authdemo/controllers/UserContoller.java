package com.example.authdemo.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserContoller {

    @GetMapping
    public String getUser(){

        return "Get user was called";
    }

    @PostMapping
    public String createUser(){
        return "Create user was called";
    }

    @PutMapping
    public String updateUser(){
        return "Update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "Delete user was called";
    }
}

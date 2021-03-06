package com.example.authdemo.controllers;


import com.example.authdemo.shared.dto.UserDto;
import com.example.authdemo.models.requestModels.CreateUserRequestModel;
import com.example.authdemo.models.responseModels.UserResponse;
import com.example.authdemo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserContoller {

    @Autowired
    UserService userService;


    @GetMapping
    public String getUser(){

        return "Get user was called";
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequestModel createUserRequestModel){

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(createUserRequestModel, userDto);

        UserDto createdUser = userService.createUser(userDto);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(createdUser, userResponse);

        return userResponse;
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

package com.example.authdemo.services;

import com.example.authdemo.dtos.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);
}

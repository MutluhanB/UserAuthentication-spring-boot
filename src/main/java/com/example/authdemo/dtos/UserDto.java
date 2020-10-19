package com.example.authdemo.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String plainPassword;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus;
}

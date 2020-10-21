package com.example.authdemo.security;

public class SecurityConstants {
    public static final long EXPIRATION_TIME_MS =  864000000; //10 days ı guess.
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user";
    public static final String TOKEN_SECRET = "n0T-s0-S3cREt";
}

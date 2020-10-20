package com.example.authdemo.shared;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public String generateUserId(){
        return generateRandomStringWithLenght(Constants.UserIdLength);
    }

    private String generateRandomStringWithLenght(int length){
        StringBuilder returnString = new StringBuilder();

        for (int i=0 ; i<length; i++){
            returnString.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnString);
    }
}

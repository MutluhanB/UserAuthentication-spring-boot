package com.example.authdemo.services.impls;

import com.example.authdemo.UserRepository;
import com.example.authdemo.shared.Utils;
import com.example.authdemo.shared.dto.UserDto;
import com.example.authdemo.entity.UserEntity;
import com.example.authdemo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity previousUser = userRepository.findByEmail(user.getEmail());

        if(previousUser !=null) {throw new RuntimeException("User already exist");}

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generateUserId());
        UserEntity storedUser = userRepository.save(userEntity);

        UserDto userResponse = new UserDto();
        BeanUtils.copyProperties(storedUser, userResponse);

        return userResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        if(user == null) { throw new UsernameNotFoundException(email);}

        return new User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());
    }
}

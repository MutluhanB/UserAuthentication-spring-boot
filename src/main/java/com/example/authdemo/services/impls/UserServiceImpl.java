package com.example.authdemo.services.impls;

import com.example.authdemo.UserRepository;
import com.example.authdemo.shared.Utils;
import com.example.authdemo.shared.dto.UserDto;
import com.example.authdemo.entity.UserEntity;
import com.example.authdemo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}

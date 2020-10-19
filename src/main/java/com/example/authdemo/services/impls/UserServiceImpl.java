package com.example.authdemo.services.impls;

import com.example.authdemo.UserRepository;
import com.example.authdemo.dtos.UserDto;
import com.example.authdemo.entity.UserEntity;
import com.example.authdemo.models.responsemodels.UserResponse;
import com.example.authdemo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setEncryptedPassword("testencpass");
        userEntity.setUserId("qwewqe");
        UserEntity storedUser = userRepository.save(userEntity);

        UserDto userResponse = new UserDto();
        BeanUtils.copyProperties(storedUser, userResponse);

        return userResponse;
    }
}

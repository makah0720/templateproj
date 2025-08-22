package com.example.template.service;

import com.example.template.domain.User;
import com.example.template.dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByUsername(String username);
}

package com.run.club.service;

import com.run.club.dto.RegistrationDto;
import com.run.club.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}

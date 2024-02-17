package com.moviereview.web.service;

import com.moviereview.web.dto.RegistrationDto;
import com.moviereview.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}

package com.miu.lab2.service;

import com.miu.lab2.entity.dto.AuthResponse;
import com.miu.lab2.entity.dto.LoginCredential;

public interface IAuthService {
    AuthResponse login(LoginCredential loginCredential);

}

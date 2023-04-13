package com.miu.lab2.controllers;

import com.miu.lab2.entity.dto.AuthResponse;
import com.miu.lab2.entity.dto.LoginCredential;
import com.miu.lab2.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginCredential loginCredential) {
        System.out.println("Login controller login()");
        var loginResponse = authService.login(loginCredential);
        return new ResponseEntity<AuthResponse>(
                loginResponse, HttpStatus.OK);
    }
}

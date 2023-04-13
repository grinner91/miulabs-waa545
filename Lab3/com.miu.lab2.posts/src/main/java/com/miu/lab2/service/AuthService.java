package com.miu.lab2.service;

import com.miu.lab2.entity.dto.AuthResponse;
import com.miu.lab2.entity.dto.LoginCredential;
import com.miu.lab2.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements  IAuthService{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginCredential loginCredential) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginCredential.getEmail(),
                            loginCredential.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException(ex.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());
        final String accessToken = jwtUtil.generateToken(userDetails);

        return new AuthResponse(accessToken, "");
    }
}

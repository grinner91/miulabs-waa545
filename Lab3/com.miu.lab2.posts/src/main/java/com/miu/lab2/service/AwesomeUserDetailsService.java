package com.miu.lab2.service;

import com.miu.lab2.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class AwesomeUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepo;

    public AwesomeUserDetailsService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByEmail(username);
        var userDetails = new AwesomeUserDetails(user);
        return userDetails;
    }

}

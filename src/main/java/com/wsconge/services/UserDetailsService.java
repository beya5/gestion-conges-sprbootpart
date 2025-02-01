package com.wsconge.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsService implements IUserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Replace with your user repository logic
        if (!username.equals("testUser")) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode("password"))
                .roles("USER")
                .build();
    }
}
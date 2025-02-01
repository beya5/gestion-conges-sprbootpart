package com.wsconge.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface IUserDetailsService {
    public UserDetails loadUserByUsername(String username) ;

    }

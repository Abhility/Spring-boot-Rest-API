package com.mindtree.springbootapi.services;

import com.mindtree.springbootapi.dao.UserRepository;
import com.mindtree.springbootapi.models.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.getUserByUserName(userName)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found:: " + userName));
    }
}

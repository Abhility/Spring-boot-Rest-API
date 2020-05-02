package com.mindtree.springbootapi.services;

import com.mindtree.springbootapi.dao.UserRepository;
import com.mindtree.springbootapi.models.ListResponse;
import com.mindtree.springbootapi.models.Response;
import com.mindtree.springbootapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Response<User> addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return new Response<>(201,"CREATED",savedUser);
    }

    public ListResponse<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return new ListResponse<>(200,"OK",users,users.size());
    }
}

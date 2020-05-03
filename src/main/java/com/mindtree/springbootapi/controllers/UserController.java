package com.mindtree.springbootapi.controllers;

import com.mindtree.springbootapi.models.ListResponse;
import com.mindtree.springbootapi.models.Response;
import com.mindtree.springbootapi.models.User;
import com.mindtree.springbootapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sample/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ListResponse<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public Response<User> addUser(@Valid @RequestBody User user){
          return userService.addUser(user);
    }
}

package com.mindtree.springbootapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "Welcome to my sample API";
    }
}

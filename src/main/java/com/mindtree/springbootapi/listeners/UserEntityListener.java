package com.mindtree.springbootapi.listeners;


import com.mindtree.springbootapi.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindtree.springbootapi.utils.UserAuthorities.*;

public class UserEntityListener {

    @PostLoad
    void setAuthorities(User user){
        Set<String> authorityValues = null;
        switch (user.getRole()){
            case "USER":
                authorityValues = new HashSet<>(Arrays.asList(
                        TOPIC_READ.getAuthority(),
                        CHAPTER_READ.getAuthority()
                        ));
                break;
            case "ADMIN":
                authorityValues = new HashSet<>(Arrays.asList(
                        USER_READ.getAuthority(),
                        USER_WRITE.getAuthority(),
                        USER_UPDATE.getAuthority(),
                        TOPIC_READ.getAuthority(),
                        TOPIC_WRITE.getAuthority(),
                        TOPIC_UPDATE.getAuthority(),
                        CHAPTER_READ.getAuthority(),
                        CHAPTER_WRITE.getAuthority(),
                        CHAPTER_UPDATE.getAuthority()));
                break;
            case "ADMINTRAINEE":
                authorityValues = new HashSet<>(Arrays.asList(
                        USER_READ.getAuthority(),
                        TOPIC_READ.getAuthority(),
                        TOPIC_WRITE.getAuthority(),
                        CHAPTER_READ.getAuthority(),
                        CHAPTER_WRITE.getAuthority()));
                break;
            default:
                authorityValues = new HashSet<>();
        }
        Set<GrantedAuthority> authorities = authorityValues.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        user.setAuthorities(authorities);
    }

    @PrePersist
    @PreUpdate
    void process(User user){
        switch (user.getType().toLowerCase()){
            case "user":
                user.setRole("USER");
                break;
            case "admin":
                user.setRole("ADMIN");
                break;
            case "admintrainee":
                user.setRole("ADMINTRAINEE");
                break;
            default:
                user.setRole("BLOCKED");
        }
        System.out.println("Setting status");
        user.setStatus(true);
        System.out.println("Current value: " + user.getStatus());
    }
}

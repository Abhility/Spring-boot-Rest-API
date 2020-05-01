package com.mindtree.springbootapi.models;

import javax.persistence.*;

@Entity
@Table(name = "User_Table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(unique = true)
    private String userName;
    private String password;
    private String role;
    private Boolean status;

    public User() {
    }

    public User(String id, String userName, String password, String role, Boolean status) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean active) {
        this.status = active;
    }

    @PrePersist
    @PreUpdate
    private void setStatus() {
        System.out.println("Setting status");
        System.out.println("Current value: " + status);
        this.status = true;
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    private void checkStatus() {
        System.out.println("Status set");
        System.out.println("Set value: " + status);
    }
}

/*
package com.mindtree.springbootapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Authority_Table")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Authority_Name", nullable = false)
    private String authorityName;

    @ManyToMany(mappedBy = "authorities")
    @JsonIgnore
    private List<User> users;

    public Authority() {

    }

    public Authority(Integer id, String authorityName, List<User> users) {
        this.id = id;
        this.authorityName = authorityName;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
*/

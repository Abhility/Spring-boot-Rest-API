package com.mindtree.springbootapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @NotBlank(message = "Chapter name cannot be null")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Chapter description cannot be null")
    private String description;

    @ManyToOne
    private Topic topic;

    public Chapter(){

    }

    public Chapter(Integer cid, @NotBlank(message = "Chapter name cannot be null") String name, @NotBlank(message = "Chapter description cannot be null") String description, Topic topic) {
        this.cid = cid;
        this.name = name;
        this.description = description;
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}

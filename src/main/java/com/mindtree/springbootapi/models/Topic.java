package com.mindtree.springbootapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Topic {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tid;

	@NotBlank(message = "Name cannot be null or empty")
	@Column(unique = true)
	private String name;

	@NotBlank(message = "Description cannot be null or empty")
	private String description;

	@OneToMany(mappedBy = "topic")
	@JsonIgnore
	private List<Chapter> chapters;

	public Topic(){

	}

	public Topic(Integer tid, String name, String description, List<Chapter> chapters) {
		this.tid = tid;
		this.name = name;
		this.description = description;
		this.chapters = chapters;
	}

	public Integer getTid() {
		return tid;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
}

package com.mindtree.springbootapi.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Topic {
	
    @Id
	@NotBlank(message = "Id cannot be null or empty")
	private String id;
	@NotBlank(message = "Name cannot be null or empty")
	private String name;
	@NotBlank(message = "Description cannot be null or empty")
	private String description;

	public Topic() {
	}

	public Topic(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

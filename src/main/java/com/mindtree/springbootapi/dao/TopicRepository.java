package com.mindtree.springbootapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.springbootapi.entities.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {

}

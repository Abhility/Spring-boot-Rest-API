package com.mindtree.springbootapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.springbootapi.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

}

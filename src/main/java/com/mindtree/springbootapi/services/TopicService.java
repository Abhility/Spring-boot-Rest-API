package com.mindtree.springbootapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.springbootapi.ListResponse;
import com.mindtree.springbootapi.Response;
import com.mindtree.springbootapi.dao.TopicRepository;
import com.mindtree.springbootapi.entities.Topic;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	public ListResponse<Topic> getTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);
		return new ListResponse<>(200, "OK", topics,topics.size());
	}

	public Response<Topic> getTopic(String id) {
		Topic topic = topicRepository.findById(id).orElse(null);
		return topic == null ? new Response<Topic>(404, "Not Found", topic) : new Response<Topic>(200, "OK", topic);
	}

	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
	}

}

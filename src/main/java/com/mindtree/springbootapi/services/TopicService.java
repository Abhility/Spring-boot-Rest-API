package com.mindtree.springbootapi.services;

import com.mindtree.springbootapi.dao.TopicRepository;
import com.mindtree.springbootapi.exceptions.ApiException;
import com.mindtree.springbootapi.models.ListResponse;
import com.mindtree.springbootapi.models.Response;
import com.mindtree.springbootapi.models.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	public ListResponse<Topic> getTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);
		return new ListResponse<>(200, "OK", topics,topics.size());
	}

	public Response<Topic> getTopic(String id){
		Topic topic = topicRepository.findById(id).orElse(null);
		if(topic!=null)
			return new Response<Topic>(200, "OK", topic);
		throw new ApiException("Topic not found", HttpStatus.NOT_FOUND);
	}

	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
	}

}

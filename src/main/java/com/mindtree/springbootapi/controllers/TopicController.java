package com.mindtree.springbootapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.springbootapi.ListResponse;
import com.mindtree.springbootapi.Response;
import com.mindtree.springbootapi.entities.Topic;
import com.mindtree.springbootapi.services.TopicService;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;

	@RequestMapping(method = RequestMethod.GET, value = "/topics")
	public ListResponse<Topic> getTopics() {
		return topicService.getTopics();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/topics/{id}")
	public Response<Topic> getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public Topic addTopic(@RequestBody Topic topic) {
		return topicService.addTopic(topic);
	}
}

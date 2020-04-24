package com.mindtree.springbootapi.controllers;

import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/sample/api")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@RequestMapping(method = RequestMethod.GET, value = "/topics")
	public ListResponse<Topic> getTopics() {
		return topicService.getTopics();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/topics/{id}")
	public Response<Topic> getTopic(@PathVariable String id, HttpServletResponse res) {
		Response<Topic> topic = topicService.getTopic(id);
		res.setStatus(topic.getStatusCode() == 200 ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NOT_FOUND);
		return topic;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public Topic addTopic(@RequestBody Topic topic) {
		return topicService.addTopic(topic);
	}
}

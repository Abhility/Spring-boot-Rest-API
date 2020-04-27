package com.mindtree.springbootapi.controllers;

import com.mindtree.springbootapi.models.ListResponse;
import com.mindtree.springbootapi.models.Response;
import com.mindtree.springbootapi.models.Topic;
import com.mindtree.springbootapi.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
		return topicService.getTopic(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public Topic addTopic(@RequestBody Topic topic) {
		return topicService.addTopic(topic);
	}
}

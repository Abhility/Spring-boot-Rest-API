package com.mindtree.springbootapi.controllers;

import com.mindtree.springbootapi.models.ListResponse;
import com.mindtree.springbootapi.models.Response;
import com.mindtree.springbootapi.models.Topic;
import com.mindtree.springbootapi.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample/api/topics")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@GetMapping
	@PreAuthorize("hasAuthority('topic:read')")
	public ListResponse<Topic> getTopics() {
		return topicService.getTopics();
	}

	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('topic:read')")
	public Response<Topic> getTopic(@PathVariable Integer id) {
		return topicService.getTopic(id);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('topic:write')")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Response<Topic> addTopic(@RequestBody Topic topic) {
		return topicService.addTopic(topic);
	}
}

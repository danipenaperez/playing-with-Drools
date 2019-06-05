package com.dppware.droolsRunner.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dppware.droolsRunner.services.TaskService;
import com.dppware.rulesKJarArtifact.bean.Task;



@RestController
@RequestMapping("task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
    public Task process(@RequestBody Task task) throws IOException {
		return taskService.process(task);
    }
	
	@GetMapping
	public Task sample() throws IOException {
		return new Task();
    }
	
}

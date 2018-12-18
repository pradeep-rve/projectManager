package com.fsd.project.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.project.manager.bo.Project;
import com.fsd.project.manager.service.ProjectManagerService;

@RestController
public class ProjectManagerController {
	
	@Autowired
	private ProjectManagerService projectManagerService;

	@GetMapping(value = "/getProjectDetails", headers = "Accept=application/json")
	public List<Project> getProjects(){
		return projectManagerService.getProjectList();
	}
}

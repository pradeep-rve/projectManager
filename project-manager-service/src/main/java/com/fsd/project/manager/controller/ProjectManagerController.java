package com.fsd.project.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.project.manager.bo.ParentTask;
import com.fsd.project.manager.bo.Project;
import com.fsd.project.manager.bo.Task;
import com.fsd.project.manager.bo.User;
import com.fsd.project.manager.exception.ProjectManagerException;
import com.fsd.project.manager.service.ProjectManagerService;

@RestController
public class ProjectManagerController {
	
	@Autowired
	private ProjectManagerService projectManagerService;

	@GetMapping(value = "/getprojectdetails", headers = "Accept=application/json")
	public List<Project> getProjects() throws ProjectManagerException{
		return projectManagerService.getProjectList();
	}
	
	@GetMapping(value = "/getuserdetails", headers = "Accept=application/json")
	public List<User> getUsersList() throws ProjectManagerException{
		return projectManagerService.getUsersList();
	}
	
	@GetMapping(value = "/gettasksdetails/{tid}", headers = "Accept=application/json")
	public List<Task> getTasksList(@PathVariable("tid") int tid) throws ProjectManagerException{
		return projectManagerService.getTasksList(tid);
	}
	
	@GetMapping(value = "/getparenttasksdetails/{pid}", headers = "Accept=application/json")
	public List<ParentTask> getParentTaskList(@PathVariable("pid") int pid) throws ProjectManagerException {
		return projectManagerService.getParentTasksList(pid);
	}
	
}

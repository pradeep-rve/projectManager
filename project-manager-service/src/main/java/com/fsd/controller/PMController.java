package com.fsd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.exception.TaskException;
import com.fsd.model.ParentTaskInfo;
import com.fsd.model.ProjectInfo;
import com.fsd.model.TaskInfo;
import com.fsd.model.UserInfo;
import com.fsd.service.ProjectManagerService;

/**
 * This class acts as a controller for exposing different endpoints of Project
 * Manager application
 * 
 * @author 449418
 *
 */
@RestController
public class PMController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PMController.class);

	@Autowired
	private ProjectManagerService projectManagerService;

	@GetMapping(value = "/getProject", headers = "Accept=application/json")
	public List<ProjectInfo> getProject() throws TaskException {
		LOGGER.debug("Inside getProject() of ProjectManagerController"); 
		return projectManagerService.getProject();
	}

	@GetMapping(value = "/getUser", headers = "Accept=application/json")
	public List<UserInfo> getUser() throws TaskException {
		LOGGER.debug("Inside getUser() of ProjectManagerController"); 
		return projectManagerService.getUser();
	}

	@GetMapping(value = "/getTask/{projectid}", headers = "Accept=application/json")
	public List<TaskInfo> getTask(@PathVariable("projectid") int projectId) throws TaskException {
		LOGGER.debug("Inside getTask() of ProjectManagerController"); 
		return projectManagerService.getTask(projectId);
	}

	@PostMapping(value = "/updateUser", headers = "Accept=application/json")
	public Boolean addOrUpdUser(@RequestBody UserInfo user) throws TaskException {
		LOGGER.debug("Inside addOrUpdUser() of ProjectManagerController"); 
		return projectManagerService.updateUserInfo(user);
	}

	@PostMapping(value = "/updateProject", headers = "Accept=application/json")
	public Boolean addOrUpdProject(@RequestBody ProjectInfo project) throws TaskException {
		LOGGER.debug("Inside addOrUpdProject() of ProjectManagerController"); 
		return projectManagerService.updateProjectInfo(project);
	}

	@PostMapping(value = "/updateTask", headers = "Accept=application/json")
	public Boolean addOrUpdTask(@RequestBody TaskInfo task) throws TaskException {
		LOGGER.debug("Inside addOrUpdTask() of ProjectManagerController"); 
		return projectManagerService.updateTaskInfo(task);
	}

	@GetMapping(value = "/getParentTask/{projectid}", headers = "Accept=application/json")
	public List<ParentTaskInfo> getParentTask(@PathVariable("projectid") int projectId) throws TaskException {
		LOGGER.debug("Inside getParentTask() of ProjectManagerController"); 
		return projectManagerService.getParentTasks(projectId);
	}

	@DeleteMapping(value = "/deleteUser/{id}", headers = "Accept=application/json")
	public Boolean deleteUser(@PathVariable("id") int userId) throws TaskException {
		LOGGER.debug("Inside deleteUser() of ProjectManagerController"); 
		return projectManagerService.deleteUser(userId);
	}

	@DeleteMapping(value = "/deleteProject/{id}", headers = "Accept=application/json")
	public Boolean deleteProject(@PathVariable("id") int projectId) throws TaskException {
		LOGGER.debug("Inside deleteProject() of ProjectManagerController"); 
		return projectManagerService.deleteProject(projectId);
	}

}

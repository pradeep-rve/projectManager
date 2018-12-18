package com.fsd.project.manager.service;

import java.util.List;

import com.fsd.project.manager.bo.ParentTask;
import com.fsd.project.manager.bo.Project;
import com.fsd.project.manager.bo.Task;
import com.fsd.project.manager.bo.User;

public interface ProjectManagerService {
	
	List<Project> getProjectList();
	
	List<User> getUsersList();

	List<Task> getTasksList(int tid);
	
	List<ParentTask> getParentTasksList(int pid);


}

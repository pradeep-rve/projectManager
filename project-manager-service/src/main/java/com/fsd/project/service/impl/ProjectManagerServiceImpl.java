package com.fsd.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsd.project.manager.bo.ParentTask;
import com.fsd.project.manager.bo.Project;
import com.fsd.project.manager.bo.Task;
import com.fsd.project.manager.bo.User;
import com.fsd.project.manager.service.ProjectManagerService;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService{

	@Override
	public List<Project> getProjectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getTasksList(int tid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParentTask> getParentTasksList(int pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	

}

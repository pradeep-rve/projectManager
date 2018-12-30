package com.fsd.dao;

import java.util.List;

import com.fsd.entity.ParentTask;
import com.fsd.entity.Project;
import com.fsd.entity.Task;
import com.fsd.entity.User;
import com.fsd.exception.TaskException;

/**
 * This interface has DAO methods required for Project Manager Application
 * 
 * @author 449418
 *
 */
public interface ProjectManagerDAO {

	public List<Project> getProjectDetails() throws TaskException;

	public List<User> getUserDetails() throws TaskException;

	public List<Task> getTaskDetails() throws TaskException;

	public List<Task> getTaskDetailsForProject(int projectId) throws TaskException;

	public List<ParentTask> getParentTasks(int projectId) throws TaskException;

	public User updateUser(User user) throws TaskException;

	public Project updateProject(Project project) throws TaskException;

	public ParentTask updateParentTask(ParentTask parentTask) throws TaskException;

	public Task updateTask(Task task) throws TaskException;

	public User getUser(int userId) throws TaskException;

	public Project getProject(int projectId) throws TaskException;

	public ParentTask getParentTask(int parentId) throws TaskException;

	boolean deleteUser(int userId) throws TaskException;

	boolean deleteProject(int projectId) throws TaskException;

	boolean deleteTask(int taskId) throws TaskException;

	boolean deleteTaskForProjectId(int projectId) throws TaskException;

	boolean deleteParentTaskForProjectId(int projectId) throws TaskException;

	boolean deleteProjectForUser(int userId) throws TaskException;
}

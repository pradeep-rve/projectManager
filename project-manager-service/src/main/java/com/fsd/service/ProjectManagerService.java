package com.fsd.service;

/**
 * Interface for service methods of Project Manager
 * 
 * @author 449418
 *
 */

import java.util.List;

import com.fsd.exception.TaskException;
import com.fsd.model.ParentTaskInfo;
import com.fsd.model.ProjectInfo;
import com.fsd.model.TaskInfo;
import com.fsd.model.UserInfo;

public interface ProjectManagerService {

	List<ProjectInfo> getProject() throws TaskException;

	List<UserInfo> getUser() throws TaskException;

	List<TaskInfo> getTask(int projectId) throws TaskException;

	Boolean updateUserInfo(UserInfo userInfo) throws TaskException;

	Boolean updateProjectInfo(ProjectInfo projectInfo) throws TaskException;

	Boolean updateTaskInfo(TaskInfo taskInfo) throws TaskException;

	List<ParentTaskInfo> getParentTasks(int projectId) throws TaskException;

	Boolean deleteUser(int userId) throws TaskException;

	Boolean deleteProject(int projectId) throws TaskException;
}

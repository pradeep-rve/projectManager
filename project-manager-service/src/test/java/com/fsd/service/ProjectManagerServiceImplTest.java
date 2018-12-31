package com.fsd.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.dao.ProjectManagerDAO;
import com.fsd.entity.ParentTask;
import com.fsd.entity.Project;
import com.fsd.entity.Task;
import com.fsd.entity.User;
import com.fsd.exception.TaskException;
import com.fsd.model.ParentTaskInfo;
import com.fsd.model.ProjectInfo;
import com.fsd.model.TaskInfo;
import com.fsd.model.UserInfo;
import com.fsd.service.ProjectManagerService;
import com.fsd.service.ProjectManagerServiceImpl;

/**
 * Test class for unit testing ProjectManager Service
 * 
 * @author 449418
 *
 */
@RunWith(SpringRunner.class)
public class ProjectManagerServiceImplTest {

	@TestConfiguration
	static class ProjectManagerServiceImplTestContextConfiguration {

		@Bean
		public ProjectManagerService projectManagerService() {
			return new ProjectManagerServiceImpl();
		}
	}

	@Autowired
	private ProjectManagerService projectManagerService;

	@MockBean
	private ProjectManagerDAO projectManagerDAOImpl;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void getProject_WithProjects()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<List<Project>> mapObj = new TypeReference<List<Project>>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projectlist.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		List<Project> projects = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getProjectDetails()).willReturn(projects);
		List<ProjectInfo> projectInfos = projectManagerService.getProject();
		assertNotNull(projectInfos);
		assertEquals(projects.get(0).getProjectId(), projects.get(0).getProjectId());
	}

	@Test
	public void getProject_WithEmptyProjects()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<List<Project>> mapObj = new TypeReference<List<Project>>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projectlist_empty.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		List<Project> projects = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getProjectDetails()).willReturn(projects);
		List<ProjectInfo> projectInfos = projectManagerService.getProject();
		assertNull(projectInfos);
	}

	@Test
	public void getProject_WithException()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<TaskException> mapObj = new TypeReference<TaskException>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("exception.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskException taskException = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getProjectDetails()).willThrow(taskException);

		thrown.expect(TaskException.class);
		projectManagerService.getProject();
	}

	@Test
	public void getUser_WithUsers() throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<List<User>> mapObj = new TypeReference<List<User>>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("userlist.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		List<User> users = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getUserDetails()).willReturn(users);
		List<UserInfo> userInfos = projectManagerService.getUser();
		assertNotNull(userInfos);
		assertEquals(users.get(0).getUserId(), userInfos.get(0).getUserId());
	}

	@Test
	public void getUser_WithException()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<TaskException> mapObj = new TypeReference<TaskException>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("exception.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskException taskException = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getUserDetails()).willThrow(taskException);

		thrown.expect(TaskException.class);
		projectManagerService.getUser();
	}

	@Test
	public void getTask_WithAllTasks() throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<List<Task>> mapObj = new TypeReference<List<Task>>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("tasklist.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		List<Task> tasks = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getTaskDetails()).willReturn(tasks);
		List<TaskInfo> taskInfos = projectManagerService.getTask(0);
		assertNotNull(taskInfos);
		assertEquals(tasks.get(0).getParentTask().getParentId(), taskInfos.get(0).getParentId());
	}

	@Test
	public void getTask_WithProjectId()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<List<Task>> mapObj = new TypeReference<List<Task>>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("tasklist.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		List<Task> tasks = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getTaskDetailsForProject(1)).willReturn(tasks);
		List<TaskInfo> taskInfos = projectManagerService.getTask(1);
		assertNotNull(taskInfos);
		assertEquals(tasks.get(0).getParentTask().getParentId(), taskInfos.get(0).getParentId());
	}

	@Test
	public void getTask_WithException()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<TaskException> mapObj = new TypeReference<TaskException>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("exception.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskException taskException = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getTaskDetailsForProject(1)).willThrow(taskException);

		thrown.expect(TaskException.class);
		projectManagerService.getTask(1);
	}

	@Test
	public void getParentTask_WithProjectId()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<List<ParentTask>> mapObj = new TypeReference<List<ParentTask>>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("parenttasklist.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		List<ParentTask> tasks = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getParentTasks(1)).willReturn(tasks);
		List<ParentTaskInfo> taskInfos = projectManagerService.getParentTasks(1);
		assertNotNull(taskInfos);
		assertEquals(tasks.get(0).getParentId(), taskInfos.get(0).getParentId());
	}

	@Test
	public void getParentTask_WithException()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<TaskException> mapObj = new TypeReference<TaskException>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("exception.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskException taskException = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.getParentTasks(1)).willThrow(taskException);

		thrown.expect(TaskException.class);
		projectManagerService.getParentTasks(1);
	}

	@Test
	public void deleteUser_Valid() throws JsonParseException, JsonMappingException, IOException, TaskException {

		given(projectManagerDAOImpl.deleteUser(1)).willReturn(true);
		assertTrue(projectManagerService.deleteUser(1));
	}

	@Test
	public void deleteUser_WithException() throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<TaskException> mapObj = new TypeReference<TaskException>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("exception.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskException taskException = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.deleteUser(1)).willThrow(taskException);

		thrown.expect(TaskException.class);
		projectManagerService.deleteUser(1);
	}

	@Test
	public void deleteProject_Valid() throws JsonParseException, JsonMappingException, IOException, TaskException {

		given(projectManagerDAOImpl.deleteProject(1)).willReturn(true);
		assertTrue(projectManagerService.deleteProject(1));
	}

	@Test
	public void deleteProject_WithException()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<TaskException> mapObj = new TypeReference<TaskException>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("exception.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskException taskException = mapper.readValue(file, mapObj);

		given(projectManagerDAOImpl.deleteProject(1)).willThrow(taskException);

		thrown.expect(TaskException.class);
		projectManagerService.deleteProject(1);
	}

	@Test
	public void updateUserInfo_Valid() throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<List<User>> mapObj = new TypeReference<List<User>>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("userlist.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		List<User> users = mapper.readValue(file, mapObj);
		given(projectManagerDAOImpl.updateUser(users.get(0))).willReturn(users.get(0));

		assertTrue(projectManagerService.updateUserInfo(getUser()));
	}

	@Test
	public void updateProjectInfo_Valid() throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<List<Project>> mapObj = new TypeReference<List<Project>>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projectlist.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		List<Project> projects = mapper.readValue(file, mapObj);
		given(projectManagerDAOImpl.getUser(1)).willReturn(projects.get(0).getUser());
		given(projectManagerDAOImpl.updateProject(projects.get(0))).willReturn(projects.get(0));
		assertTrue(projectManagerService.updateProjectInfo(getProject()));
	}

	@Test
	public void updateProjectInfo_WithException()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<TaskException> mapObj = new TypeReference<TaskException>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("exception.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskException taskException = mapper.readValue(file, mapObj);
		given(projectManagerDAOImpl.getUser(1)).willThrow(taskException);
		given(projectManagerDAOImpl.updateProject(new Project())).willThrow(taskException);

		thrown.expect(TaskException.class);
		projectManagerService.updateProjectInfo(getProject());
	}

	@Test
	public void updateTaskInfo_Valid() throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<List<Task>> mapObj = new TypeReference<List<Task>>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("tasklist.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		List<Task> tasks = mapper.readValue(file, mapObj);
		Task task = tasks.get(0);
		given(projectManagerDAOImpl.getProject(1)).willReturn(tasks.get(0).getProject());
		given(projectManagerDAOImpl.getParentTask(1)).willReturn(tasks.get(0).getParentTask());
		given(projectManagerDAOImpl.updateTask(task)).willReturn(task);
		assertTrue(projectManagerService.updateTaskInfo(getTask()));
	}

	@Test
	public void updateTaskInfo_WithException()
			throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<TaskException> mapObj = new TypeReference<TaskException>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("exception.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskException taskException = mapper.readValue(file, mapObj);
		given(projectManagerDAOImpl.getProject(1)).willThrow(taskException);
		given(projectManagerDAOImpl.getParentTask(1)).willThrow(taskException);
		given(projectManagerDAOImpl.updateTask(new Task())).willThrow(taskException);

		thrown.expect(TaskException.class);
		projectManagerService.updateTaskInfo(getTask());
	}

	private UserInfo getUser() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		TypeReference<UserInfo> mapObj = new TypeReference<UserInfo>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("user.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		UserInfo userInfo = mapper.readValue(file, mapObj);
		return userInfo;
	}

	private TaskInfo getTask() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		TypeReference<TaskInfo> mapObj = new TypeReference<TaskInfo>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("taskinfo.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskInfo taskInfo = mapper.readValue(file, mapObj);
		return taskInfo;
	}

	private ProjectInfo getProject() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		TypeReference<ProjectInfo> mapObj = new TypeReference<ProjectInfo>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projectinfo.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		ProjectInfo projectInfo = mapper.readValue(file, mapObj);
		return projectInfo;
	}

	@Test
	public void updateParentTaskInfo_Valid() throws JsonParseException, JsonMappingException, IOException, TaskException, URISyntaxException {
		TypeReference<TaskInfo> mapObj = new TypeReference<TaskInfo>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("parenttaskinfo.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskInfo task = mapper.readValue(file, mapObj);
		given(projectManagerDAOImpl.getProject(1)).willReturn(new Project());
		ParentTask parentTask = new ParentTask();
		given(projectManagerDAOImpl.updateParentTask(parentTask)).willReturn(parentTask);
		assertTrue(projectManagerService.updateTaskInfo(task));
	}
}

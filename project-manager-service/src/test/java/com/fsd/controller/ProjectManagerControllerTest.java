package com.fsd.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.controller.PMController;
import com.fsd.model.ParentTaskInfo;
import com.fsd.model.ProjectInfo;
import com.fsd.model.TaskInfo;
import com.fsd.model.UserInfo;
import com.fsd.service.ProjectManagerService;

/**
 * Test class for unit testing ProjectManager Controller
 * 
 * @author 449418
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PMController.class)
public class ProjectManagerControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProjectManagerService projectManagerServiceImpl;

	@Test
	public void getProject_thenReturnJsonArray() throws Exception {

		ProjectInfo projectInfo = getProject();

		List<ProjectInfo> projects = Arrays.asList(projectInfo);

		given(projectManagerServiceImpl.getProject()).willReturn(projects);

		mvc.perform(get("/getProject").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].projectName", is(projectInfo.getProjectName())));
	}

	@Test
	public void getUser_thenReturnJsonArray() throws Exception {

		UserInfo userInfo = getUser();

		List<UserInfo> users = Arrays.asList(userInfo);

		given(projectManagerServiceImpl.getUser()).willReturn(users);

		mvc.perform(get("/getUser").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].userId", is(userInfo.getUserId())));
	}

	@Test
	public void getTask_thenReturnJsonArray() throws Exception {

		TaskInfo taskInfo = getTask();

		List<TaskInfo> tasks = Arrays.asList(taskInfo);

		given(projectManagerServiceImpl.getTask(1)).willReturn(tasks);

		mvc.perform(get("/getTask/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].taskName", is(taskInfo.getTaskName())));

	}

	@Test
	public void getParentTask_thenReturnJsonArray() throws Exception {

		ParentTaskInfo taskInfo = getParentTask();

		List<ParentTaskInfo> tasks = Arrays.asList(taskInfo);

		given(projectManagerServiceImpl.getParentTasks(1)).willReturn(tasks);

		mvc.perform(get("/getParentTask/{projectId}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].parentTaskName", is(taskInfo.getParentTaskName())));

	}

	@Test
	public void getParentTask_InvalidArgument() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getParentTask/f").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addOrUpdUser_updateUser() throws Exception {
		UserInfo userInfo = getUser();
		userInfo.setUserId(0);
		given(projectManagerServiceImpl.updateUserInfo(userInfo)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/updateUser").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userInfo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void addOrUpdUser_addUser() throws Exception {
		UserInfo userInfo = getUser_Add();

		given(projectManagerServiceImpl.updateUserInfo(userInfo)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/updateUser").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userInfo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void updateProject_editProject() throws Exception {
		ProjectInfo projectInfo = getProject();

		given(projectManagerServiceImpl.updateProjectInfo(projectInfo)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/updateProject").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(projectInfo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void updateProject_addProject() throws Exception {
		ProjectInfo projectInfo = getProject_Add();
		given(projectManagerServiceImpl.updateProjectInfo(projectInfo)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/updateProject").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(projectInfo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void updateTask_editTask() throws Exception {
		TaskInfo taskInfo = getTask();

		given(projectManagerServiceImpl.updateTaskInfo(taskInfo)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/updateTask").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(taskInfo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void updateTask_addTask() throws Exception {
		TaskInfo taskInfo = getTask_Add();
		given(projectManagerServiceImpl.updateTaskInfo(taskInfo)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/updateTask").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(taskInfo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void updateTask_suspendTask() throws Exception {
		TaskInfo taskInfo = getTask_Suspend();
		given(projectManagerServiceImpl.updateTaskInfo(taskInfo)).willReturn(true);
		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/updateProject").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(taskInfo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void deleteProject_Success() throws Exception {

		given(projectManagerServiceImpl.deleteProject(1)).willReturn(true);

		mvc.perform(delete("/deleteProject/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void deleteUser_Success() throws Exception {

		given(projectManagerServiceImpl.deleteUser(1)).willReturn(true);

		mvc.perform(delete("/deleteUser/{id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

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

	private ProjectInfo getProject_Add() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		TypeReference<ProjectInfo> mapObj = new TypeReference<ProjectInfo>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("projectinfo_add.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		ProjectInfo projectInfo = mapper.readValue(file, mapObj);
		return projectInfo;
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

	private UserInfo getUser_Add() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		TypeReference<UserInfo> mapObj = new TypeReference<UserInfo>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("user_add.json").toURI());
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

	private TaskInfo getTask_Add() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		TypeReference<TaskInfo> mapObj = new TypeReference<TaskInfo>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("taskinfo_add.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskInfo taskInfo = mapper.readValue(file, mapObj);
		return taskInfo;
	}

	private TaskInfo getTask_Suspend() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		TypeReference<TaskInfo> mapObj = new TypeReference<TaskInfo>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("taskinfo_suspend.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		TaskInfo taskInfo = mapper.readValue(file, mapObj);
		return taskInfo;
	}

	private ParentTaskInfo getParentTask() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		TypeReference<ParentTaskInfo> mapObj = new TypeReference<ParentTaskInfo>() {
		};
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("parenttask.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		ParentTaskInfo parentTaskInfo = mapper.readValue(file, mapObj);
		return parentTaskInfo;

	}

}

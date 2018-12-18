package com.fsd.project.manager.test;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.project.manager.bo.ParentTask;
import com.fsd.project.manager.bo.Project;
import com.fsd.project.manager.bo.Task;
import com.fsd.project.manager.bo.User;
import com.fsd.project.manager.controller.ProjectManagerController;
import com.fsd.project.manager.service.ProjectManagerService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectManagerController.class)
public class ProjectManagerControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProjectManagerService projectManagerServiceImpl;
	
	@Test
	public void testGetProject() throws Exception {
		Project project = buildProjectList();
		List<Project> projects = new ArrayList<>();
		given(projectManagerServiceImpl.getProjectList()).willReturn(projects);
		projects.add(project);
		mvc.perform(get("/getprojectdetails").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetUser() throws Exception {
		User user = buildUserList();
		List<User> users = new ArrayList<>();
		given(projectManagerServiceImpl.getUsersList()).willReturn(users);
		users.add(user);
		mvc.perform(get("/getuserdetails").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetTask() throws Exception{
		Task task= buildTaskList();
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		given(projectManagerServiceImpl.getTasksList(1)).willReturn(tasks);
		mvc.perform(get("/gettasksdetails/{tid}",1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetParentTask() throws Exception{
		ParentTask parentTask= buildParentTask();
		List<ParentTask> parentTasks = new ArrayList<>();
		parentTasks.add(parentTask);
		given(projectManagerServiceImpl.getParentTasksList(1)).willReturn(parentTasks);
		mvc.perform(get("/getparenttasksdetails/{pid}",1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	private Project buildProjectList() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("getproject.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		Project project = mapper.readValue(file,Project.class);
		return project;
	}
	
	private User buildUserList() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("getuser.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		User userInfo = mapper.readValue(file, User.class);
		return userInfo;
	}
	
	private Task buildTaskList() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("gettask.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		Task task = mapper.readValue(file, Task.class);
		return task;
	}
	
	private ParentTask buildParentTask() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("getparenttask.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		ParentTask parentTask = mapper.readValue(file, ParentTask.class);
		return parentTask;
	}
	
	

}

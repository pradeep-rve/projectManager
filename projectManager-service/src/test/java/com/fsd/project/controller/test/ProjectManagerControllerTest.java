package com.fsd.project.controller.test;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.project.manager.bo.Project;
import com.fsd.project.service.ProjectManagerService;

@RunWith(SpringRunner.class)
public class ProjectManagerControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProjectManagerService projectManagerServiceImpl;
	
	@Test
	public void testGetProject() throws Exception {
		Project project = buildProjectVO();
		List<Project> projects = new ArrayList<>();
		given(projectManagerServiceImpl.getProjectList()).willReturn(projects);
		projects.add(project);
		mvc.perform(get("getProject").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	private Project buildProjectVO() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("getproject.json").getFile());
		ObjectMapper mapper = new ObjectMapper();
		Project project = mapper.readValue(file,Project.class);
		return project;
	}

}

package com.fsd.project.manager.bo;

import java.util.Date;

public class Project {
	
	private int projectId;
	private String projectName;
	private int totalTasks;
	private int noOfCompletedTasks;
	private Date startDate;
	private Date endDate;
	private int priority;
	private int userId;
	private String userName;
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getTotalTasks() {
		return totalTasks;
	}
	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}
	public int getNoOfCompletedTasks() {
		return noOfCompletedTasks;
	}
	public void setNoOfCompletedTasks(int noOfCompletedTasks) {
		this.noOfCompletedTasks = noOfCompletedTasks;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}

package com.dppware.rulesKJarArtifact.bean;

import java.io.Serializable;
import java.util.Date;


public class Task implements Serializable{
	
	private String title;
	private String description;
	private Date createdOn;
	private String status;
	private Employee assignedTo;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Employee getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}
	@Override
	public String toString() {
		return "Task [title=" + title + ", description=" + description + ", createdOn=" + createdOn + ", status="
				+ status + ", assignedTo=" + assignedTo + "]";
	}
	
}

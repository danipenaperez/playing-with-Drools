package com.dppware.rulesKJarArtifact.bean;


public class Employee {
	private String name;
	private String role;
	
	public Employee() {
		super();
	}
	
	public Employee(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", role=" + role + "]";
	}
	
}

package com.dppware.rulesKJarArtifact.provider;

import com.dppware.rulesKJarArtifact.bean.Employee;

public interface IEmployeeProvider {
	public Employee getEmployeeByRole(String role); 
}

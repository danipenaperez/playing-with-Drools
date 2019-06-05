package com.dppware.droolsRunner.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dppware.rulesKJarArtifact.bean.Employee;
import com.dppware.rulesKJarArtifact.provider.IEmployeeProvider;

@Service
public class EmployeeService implements IEmployeeProvider{

	private List<Employee> employees = Arrays.asList(new Employee("Marisa", "officer"), new Employee("Jose", "officer"));
			
	
	public List<Employee> getAllEmployees() {
		return employees;
	}
	
	public Employee getEmployeeByRole(String role) {
		return employees.stream().filter(e->e.getRole().equals(role)).collect(Collectors.toList()).get(0);
	}
	
}

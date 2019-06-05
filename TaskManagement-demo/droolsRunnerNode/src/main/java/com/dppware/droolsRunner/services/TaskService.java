package com.dppware.droolsRunner.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.dppware.rulesKJarArtifact.bean.Task;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService {

	@Autowired
	private KieContainer KsessionServices;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private NotificationServices notificationService;
	
	public Task process(@RequestBody Task task) throws IOException {
		StatelessKieSession session = KsessionServices.newStatelessKieSession();
		
		KieRuntimeLogger kieLogger = KieServices.get().getLoggers().newFileLogger(session, "droolsLogger");
		
		session.setGlobal("employeeProvider", employeeService);
		session.setGlobal("notificationService", notificationService);
		
		//Prepare facts to insert
		List<Object> facts = new ArrayList<Object>();
		facts.add(task);
		//Execute all rules
		session.execute(facts);
		kieLogger.close();
		return task;
    }
}

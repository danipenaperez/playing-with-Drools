package com.dppware.droolsRunner.services;

import org.springframework.stereotype.Service;

import com.dppware.rulesKJarArtifact.bean.Employee;
import com.dppware.rulesKJarArtifact.provider.INotificationServices;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class NotificationServices implements INotificationServices{
	@Override
	public void sendNotification(Employee employee, String title) {
		log.info("Succesfully send notification to {} [New task in your inbox: {} ]",employee.getName(),  title);
	}

}

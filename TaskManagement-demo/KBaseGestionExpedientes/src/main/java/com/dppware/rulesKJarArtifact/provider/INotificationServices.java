package com.dppware.rulesKJarArtifact.provider;

import com.dppware.rulesKJarArtifact.bean.Employee;
import com.dppware.rulesKJarArtifact.bean.Task;

public interface INotificationServices {
	public void sendNotification(Employee employee, String title);
}

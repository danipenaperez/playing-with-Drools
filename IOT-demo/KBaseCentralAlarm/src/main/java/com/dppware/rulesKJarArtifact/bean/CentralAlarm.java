package com.dppware.rulesKJarArtifact.bean;

import java.util.HashMap;
import java.util.Map;

import com.dppware.rulesKJarArtifact.bean.device.Device;


public class CentralAlarm extends Device{
	public CentralAlarm(String id, String status) {
		this.id=id;
		this.status=status;
	}


	
	Map<String, Device> devices = new HashMap<String, Device>();

	

	public Map<String, Device> getDevices() {
		return devices;
	}

	public void setDevices(Map<String, Device> devices) {
		this.devices = devices;
	}
}

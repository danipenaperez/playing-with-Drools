package com.dppware.rulesKJarArtifact.bean.device;

public class Light extends Device{

	public Light(String id, String initialStatus) {
		this.id=id;
		this.status = initialStatus;
	}
	
	public void on() {
		this.status="on";
	}
	
	public void off() {
		this.status="off";
	}
}

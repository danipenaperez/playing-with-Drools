package com.dppware.rulesKJarArtifact.bean.device;

import java.io.Serializable;

public class Device implements Serializable{
	protected String id;
	protected String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

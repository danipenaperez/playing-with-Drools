package com.dppware.droolsRunner.dto;

import java.io.Serializable;
/**
 * Simple POJO
 * @author dpena
 *
 */

public class DeviceEvent implements Serializable{
	
	private String id;
	
	private String status;

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

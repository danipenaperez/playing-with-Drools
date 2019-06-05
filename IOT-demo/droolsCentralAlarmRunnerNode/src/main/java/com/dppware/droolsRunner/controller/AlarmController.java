package com.dppware.droolsRunner.controller;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dppware.droolsRunner.dto.DeviceEvent;
import com.dppware.droolsRunner.services.CentralAlarmService;
import com.dppware.rulesKJarArtifact.bean.CentralAlarm;

@RestController
@RequestMapping("alarm")
public class AlarmController {
	
	@Autowired
	CentralAlarmService centralAlarm;
	
	@GetMapping
	public CentralAlarm getAlarm() {
		return centralAlarm.getAlarm();
    }
	
	@PostMapping(path="/reset")
	@PermitAll
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void receiveDeviceEvent() {
		centralAlarm.updateAlarm("CentralAlarm1", "ready");
    }
	

}

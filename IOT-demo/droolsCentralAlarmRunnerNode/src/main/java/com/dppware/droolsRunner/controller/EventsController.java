package com.dppware.droolsRunner.controller;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dppware.droolsRunner.dto.DeviceEvent;
import com.dppware.droolsRunner.services.CentralAlarmService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("events")
@Slf4j
public class EventsController {
	
	@Autowired
	CentralAlarmService centralAlarmService;

	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	@PermitAll
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void receiveDeviceEvent(@RequestBody DeviceEvent deviceEvent) {
		//DoorLock device = (DoorLock)centralAlarmService.getDeviceById("EntranceLock");
		//device.setStatus("open");
		centralAlarmService.updateDevice(deviceEvent.getId(), deviceEvent.getStatus());

    }
}

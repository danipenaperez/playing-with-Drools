package com.dppware.droolsRunner.services;

import javax.annotation.PostConstruct;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dppware.rulesKJarArtifact.bean.CentralAlarm;
import com.dppware.rulesKJarArtifact.bean.device.Device;
import com.dppware.rulesKJarArtifact.bean.device.DoorLock;
import com.dppware.rulesKJarArtifact.bean.device.Light;
import com.dppware.rulesKJarArtifact.bean.device.Siren;

@Service
public class CentralAlarmService {

	private CentralAlarm alarm;
	
	KieSession kieSession;
	
	@Autowired
    private KieContainer kieContainer;
	
	@PostConstruct
	private void init() {
		
		kieSession = kieContainer.newKieSession();
		
		//Add Central Alarm
		alarm = new CentralAlarm("CentralAlarm1", null);
		kieSession.insert(alarm);
				
		//Add Entrance DoorLock
		DoorLock lock = new DoorLock("EntranceLock",null);
		kieSession.insert(lock);
		alarm.getDevices().put(lock.getId(), lock);
		
		//Add Ligth
		Light ligth = new Light("EntranceLight",null);
		kieSession.insert(ligth);
		alarm.getDevices().put(ligth.getId(), ligth);
		
		//Add Back DoorLock
		DoorLock backDoorEntrance = new DoorLock("BackDoorEntrance",null);
		kieSession.insert(backDoorEntrance);
		alarm.getDevices().put(backDoorEntrance.getId(), backDoorEntrance);
		
		//Add Siren
		Siren siren = new Siren("Siren");
		kieSession.insert(siren);
		alarm.getDevices().put(siren.getId(), siren);
    	
    	new Thread() {
    		@Override
    		public void run() {
    			kieSession.fireUntilHalt();
    		}
    	}.start();
    	
	}
	
	public CentralAlarm getAlarm() {
		return alarm;
	}
	
	public Device updateDevice(String id, String newStatus) {
		Device obj =  (Device) alarm.getDevices().get(id);
		obj.setStatus(newStatus);
		FactHandle fh= kieSession.getFactHandle(obj);
		kieSession.update(fh, obj);
		return alarm.getDevices().get(id);
	}
	public CentralAlarm updateAlarm(String id, String newStatus) {
		alarm.setStatus(newStatus);
		FactHandle fh= kieSession.getFactHandle(alarm);
		kieSession.update(fh, alarm);
		return this.alarm;
	}
	
	
}

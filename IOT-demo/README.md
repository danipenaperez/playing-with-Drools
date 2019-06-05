Statefull demo, using a KJar *KBaseCentralAlarm*  and the Runner *droolsCentralAlarmRunnerNode*
-----------------------

This demo simulate a IOT house project with some devices and some rules to manage all. The states of the devices can infer executions and different scenarios.


* **Build**:

1.Generate the KJar artifact that contains the KnoledgeBase (Will generate the KJar Artifact (KBaseCentralAlarm-1.0.0.jar) at .m2):
```
./KBaseCentralAlarm (IOT-demo) $ mvn clean install
```

2.Start up the Drools Runner web app (wait until "Started DroolsDemoApplication"): 
```
./droolsCentralAlarmRunnerNode (IOT-demo) $ mvn spring-boot:run
```



* **Playing with it**:

0.On startup some devices are added to the session, some message are printed at console:
```
Sucessfully added central alarm to monitoring rules engine
Sucessfully added Locked BackDoorEntranceto the system
Sucessfully added Locked EntranceLockto the system
Sucessfully added Locked EntranceLightto the system
Sucessfully added Locked Sirento the system
Central Alarm is Ready

```
1.Check the current device status
```
curl -X GET -i http://localhost:8080/alarm

{
  "id": "CentralAlarm1",
  "status": "ready",
  "devices": {
    "Siren": {
      "id": "Siren",
      "status": null
    },
    "EntranceLock": {
      "id": "EntranceLock",
      "status": "closed"
    },
    "EntranceLight": {
      "id": "EntranceLight",
      "status": "off"
    },
    "BackDoorEntrance": {
      "id": "BackDoorEntrance",
      "status": "closed"
    }
  }
}
```

1. Check the rules and for example send a EntranceDoor opening event, to see that the light will be switch on 
the rule definition
```
/**
* If entrance Door open, the entrance ligth is switch on
**/
rule " If entrance Door open, the entrance ligth is switch on"
	when
     	$dl : DoorLock(id == "EntranceLock" && status == "open")
     	$ligth: Light()
    then
    	$ligth.on()
end
```

Test it!!
```
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/events --data '{"id":"EntranceLock","status":"open"}'
```
Now Request /alarm to get all devices status (**verify EntranceLock is open and EntranceLigth is on**) :
```
curl -X GET -i http://localhost:8080/alarm

{
  "id": "CentralAlarm1",
  "status": "ready",
  "devices": {
    "Siren": {
      "id": "Siren",
      "status": null
    },
    "EntranceLock": {
      "id": "EntranceLock",
      "status": "open"
    },
    "EntranceLight": {
      "id": "EntranceLight",
      "status": "on"
    },
    "BackDoorEntrance": {
      "id": "BackDoorEntrance",
      "status": "closed"
    }
  }
}
```

2. Other rule fire the alarm if de BackDoorLock is open and the Alarm fired status infered other executions

the rule definition
```
/**
* If the backDoor is open fire the alarm
**/
rule "If the backDoor is open fire the alarm"
	when
     	$dl : DoorLock(id == "BackDoorEntrance"  && status == "open")
     	$ca : CentralAlarm(status == "ready")
    then
		System.out.println("The Back Door is open");
		
		modify($ca){
			status = "fired";
		};    	
end

......

/**
* when the Alarm is fired will shout each 2 seconds
**/
rule "Siren_2"
	timer ( int: 2s 2s )
	when
     	$alarm : CentralAlarm(status == "fired")
     	$siren: Siren()
    then
   		$siren.trigger();
   		
end


```

Test it!!
```
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/events --data '{"id":"BackDoorEntrance","status":"open"}'
```
Now Request /alarm to get all devices status (**verify centralAlarm is Fired and the Siren is running**) :
```
curl -X GET -i http://localhost:8080/alarm

{
  "id": "CentralAlarm1",
  "status": "fired",
  "devices": {
    "Siren": {
      "id": "Siren",
      "status": null
    },
    "EntranceLock": {
      "id": "EntranceLock",
      "status": "open"
    },
    "EntranceLight": {
      "id": "EntranceLight",
      "status": "on"
    },
    "BackDoorEntrance": {
      "id": "BackDoorEntrance",
      "status": "open"
    }
  }
}
```
and at the console will see the repetated Siren messages:
```
****************************
* ALARM!!! ALARM!! ALARM!! *
****************************
****************************
* ALARM!!! ALARM!! ALARM!! *
****************************
....
```

Restore to normal State 
1. Close the BackDoor
```
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/events --data '{"id":"BackDoorEntrance","status":"closed"}'
```
2.Reset the Alarm (**notice that if you reset the alarm without close the BackDoor the Alarm will be fired again because the Backdoor rule is satisfied**)
```
curl -X POST -i http://localhost:8080/alarm/reset
```
And you will see the Central Alarm is ready message at the console, and stop the Siren :
```
Central Alarm is Ready
```
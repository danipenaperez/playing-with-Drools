Stateless demo, using a KJar *KBaseGestionExpedientes*  and the Runner *droolsRunnerNode*
-----------------------
This demo simulate a create Task Flow rules. A task is created and some rules of different departments will be triggered.
This demo show how to deploy new rule version without stop the engine.

* **Build**:

1.Generate the KJar artifact that contains the KnoledgeBase (Will generate the KJar Artifact (KBaseGestionExpedientes-1.0.0.jar) at .m2):
```
./KBaseGestionExpedientes (TaskManagement-demo) $ mvn clean install
```

2.Start up the Drools Runner web app (wait until "Started DroolsDemoApplication"): 
```
./droolsRunnerNode (TaskManagement-demo) $ mvn spring-boot:run
```



* **Playing with it**:

1. Post a simple task and the task will be modified and returned based on KBaseGestionExpedientes rules
```
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/task --data '{
  "title": "First Important Task",
  "description": "Do a important task at the office"
}'
```
The response :
```
{
  "title": "First Important Task",
  "description": "Do a important task at the office .",
  "createdOn": "2019-06-04T08:42:20.030+0000",
  "status": "open",
  "assignedTo": {
    "name": "Marisa",
    "role": "officer"
  }
}
```
At logs will be printed the notification execution
```
c.d.d.services.NotificationServices      : Succesfully send notification to Marisa [New task in your inbox: FIRST IMPORTANT TASK ]
```


* **HOT Reloading Rules**:

Open other terminal and **don't stop the droolsRunner process**. Now, in teamRules.drl adjust the **date-effective** and **date-expire** values to force rule execution

```
rule "Jira_433 2 of June amazing message"
	date-effective "2019/06/04" 
	date-expires "2019/06/05"
	when
     	$t : Task()
    then
    	$t.setDescription($t.getDescription()+" .This Task has been created in our Company 25th Anniversary");
    	modify($t)
end
```
Update KBaseGestionExpedientes/pom.xml to the next version 
```
<version>1.0.1</version>
```
Recompile: 
```
mvn clean install 
```
And at the logs you will see that the KieScanner found the LATEST artifact version updated , so will fetch and reload:
```
Scanner Monitoring Status =  KieScannerStatusChangeEvent [status=UPDATING]
2019-06-04 11:07:37.095  INFO 9744 --- [        Timer-0] c.d.d.config.BPMConfigurations           : Successfully loaded LATEST VERSION of com.dppware.KBaseGestionExpedientes, all new sessions created will store the new Rules
2019-06-04 11:07:37.095  INFO 9744 --- [        Timer-0] org.kie.api.builder.KieScanner           : The following artifacts have been updated: {com.dppware:KBaseGestionExpedientes:1.0.0=com.dppware:KBaseGestionExpedientes:jar:1.0.1}
Scanner Monitoring Status =  KieScannerStatusChangeEvent [status=RUNNING]
```

Now retry the first curl and see that the description message changed because the rule match and is fired:
```
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/task --data '{
  "title": "First Important Task",
  "description": "Do a important task at the office"
}'
```
The new respose 
```
{
  "title": "First Important Task",
  "description": "Do a important task at the office .This Task has been created in our Company 25th Anniversary",
  "createdOn": "2019-06-04T09:10:21.243+0000",
  "status": "open",
  "assignedTo": {
    "name": "Marisa",
    "role": "officer"
  }
}
```



* **Unit Testing**:

RuleTest.java contains a simple test to force execute single Rule named "Jira_167 Default Status if not exists", based on AgendaFilter feature:
```
>mvn clean test
```
And then the rule only set the default status, and the other rules are not fired
```
>[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
>INFO droolsRunnerNode.RulesTest - Task [title=Execute task A, description=Do amazing things at home., createdOn=null, status=open, assignedTo=null]
```

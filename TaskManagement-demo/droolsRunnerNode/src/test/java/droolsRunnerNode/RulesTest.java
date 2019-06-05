package droolsRunnerNode;

import org.junit.Test;
import org.junit.Assert;;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import org.springframework.test.context.junit4.SpringRunner;

import com.dppware.rulesKJarArtifact.bean.Task;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
public class RulesTest {

	static {
		System.setProperty("drools.dateformat", "yyyy/MM/dd");
	}
	
	
	private KieContainer kieContainer;
	
	private KieContainer getKieContainer() {
		if(this.kieContainer==null) {
			KieServices ks = KieServices.Factory.get();
			this.kieContainer = ks.newKieClasspathContainer();
		}
		return this.kieContainer;
	}

	 
	
	@Test
	public void whenCreatedNewTask_thenDefaultStatusIsOpen() {
		Task task = new Task();
		task.setTitle("Execute task A");
		task.setDescription("Do amazing things at home.");
		
		KieSession session = getKieContainer().newKieSession();
		
		session.insert(task);
		session.fireAllRules(new AgendaFilter() {
			@Override
			public boolean accept(Match match) {
				return match.getRule().getName().contains("Jira_167 Default Status if not exists");
			}
		});
		
		
		Assert.assertEquals(task.getStatus(), "open"); //the rule is fired
		Assert.assertNull(task.getAssignedTo()); //the other rules are not fired 
		
		log.info(task.toString());
		
	}
}

package com.dppware.droolsRunner.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.event.kiescanner.KieScannerEventListener;
import org.kie.api.event.kiescanner.KieScannerStatusChangeEvent;
import org.kie.api.event.kiescanner.KieScannerUpdateResultsEvent;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BPMConfigurations {

	
	@Bean
	public KieContainer kieContainer() {
		log.info("Loading KieServices..");
		KieServices ks = KieServices.Factory.get();
		
		final ReleaseId releaseId = ks.newReleaseId("com.dppware", "KBaseCentralAlarm", "LATEST");
		final KieContainer kc = ks.newKieContainer(releaseId);
		
		log.info("Succesfully loaded KIE Environment");
		
		//Prepare hook for hot reloading
		KieScanner kScanner = ks.newKieScanner( kc );
		kScanner.start(1000l);// Start the KieScanner polling the Maven repository in provided time
		kScanner.addListener(new KieScannerEventListener() {
			
			public void onKieScannerUpdateResultsEvent(KieScannerUpdateResultsEvent updateResults) {
				
				try {
					ReleaseId rel = KieServices.Factory.get().newReleaseId("com.dppware", "KBaseCentralAlarm", "LATEST");
					Results res = kc.updateToVersion(rel);
					if(res.getMessages().size()>0) {//If some fails will be reported on Results object, and the v
						log.info(res.getMessages().toString());
					}else {
						log.info("Successfully loaded LATEST VERSION of {}, all new sessions created will store the new Rules", rel);
					}
				}catch(Exception e) {
					
				}
			}
			
			public void onKieScannerStatusChangeEvent(KieScannerStatusChangeEvent statusChange) {
				//System.out.println("Scanner Monitoring Status =  "+statusChange);
			}
		});

		return kc;
	}

}

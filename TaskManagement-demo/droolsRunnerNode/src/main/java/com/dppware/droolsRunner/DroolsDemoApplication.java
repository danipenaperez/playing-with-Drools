package com.dppware.droolsRunner;

import java.io.IOException;

import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.internal.io.ResourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Wrapper
 * @author dpena
 *
 */
@SpringBootApplication
public class DroolsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroolsDemoApplication.class, args);
	}
}

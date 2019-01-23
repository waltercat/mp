package com.tsingglobal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TsingglobalAdminApplication {

	public static void main(String[] args) {
		cac = SpringApplication.run(TsingglobalAdminApplication.class, args);
	}
	
	public static ConfigurableApplicationContext cac;
}

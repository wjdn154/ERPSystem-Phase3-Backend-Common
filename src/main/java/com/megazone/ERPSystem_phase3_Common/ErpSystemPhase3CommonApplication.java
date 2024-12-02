package com.megazone.ERPSystem_phase3_Common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {BatchAutoConfiguration.class})
public class ErpSystemPhase3CommonApplication {
	public static void main(String[] args) {

		SpringApplication.run(ErpSystemPhase3CommonApplication.class, args);
	}
}
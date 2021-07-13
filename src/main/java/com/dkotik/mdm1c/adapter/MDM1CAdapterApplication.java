package com.dkotik.mdm1c.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.dkotik.mdm1c.adapter")
public class MDM1CAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MDM1CAdapterApplication.class, args);
	}

}

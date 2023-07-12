package com.oscarjimenez.datamanageproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients()
public class DatamanageprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatamanageprojectApplication.class, args);
	}

}

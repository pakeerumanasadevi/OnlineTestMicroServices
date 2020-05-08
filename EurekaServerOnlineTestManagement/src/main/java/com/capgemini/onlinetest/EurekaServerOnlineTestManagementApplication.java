package com.capgemini.onlinetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerOnlineTestManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerOnlineTestManagementApplication.class, args);
	}

}

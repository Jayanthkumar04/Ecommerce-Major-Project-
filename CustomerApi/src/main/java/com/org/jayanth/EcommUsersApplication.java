package com.org.jayanth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcommUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommUsersApplication.class, args);
	}

}

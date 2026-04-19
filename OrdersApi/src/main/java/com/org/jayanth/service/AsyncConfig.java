package com.org.jayanth.service;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	
	
	@Bean(name="emailExecutor")
	public Executor emailExecutor()
	{
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		
		executor.setCorePoolSize(5); //start threads
		
		executor.setMaxPoolSize(10); // max threads
		
		executor.setQueueCapacity(200);//queue before creating new threads
		
		executor.setThreadNamePrefix("Email-Thread-");
	
		executor.initialize();
		
		return executor;
		
		
	}

}

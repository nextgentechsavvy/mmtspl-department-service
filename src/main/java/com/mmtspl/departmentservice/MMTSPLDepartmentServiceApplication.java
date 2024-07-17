package com.mmtspl.departmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.annotation.Bean;
//import brave.sampler.Sampler;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@EnableDiscoveryClient
//@EnableFeignClients("com.mmtspl.departmentservice.mmtspl-department-service")
public class MMTSPLDepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MMTSPLDepartmentServiceApplication.class, args);
	}
	
/*
	//creating a bean
	@Bean  
	//creating a sampler called   
	public Sampler defaultSampler()  
	{  
		return Sampler.ALWAYS_SAMPLE;  
	} 	
*/

}

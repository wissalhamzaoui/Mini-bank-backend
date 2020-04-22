package com.example.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = MongoProperties.class)

public class MiniBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniBankApplication.class, args);
	}

}

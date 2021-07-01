package com.neonzoff.DemoThymeleaf;

import com.neonzoff.DemoThymeleaf.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoThymeleafApplication {

	@Autowired
	UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoThymeleafApplication.class, args);
	}

}

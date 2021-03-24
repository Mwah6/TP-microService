package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class MicroServiceCustomerServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroServiceCustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start (CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(Customer.class);
			customerRepository.save(new Customer(null, "Bob", "bob@mail.com"));
			customerRepository.save(new Customer(null, "Patrick", "patrick@mail.com"));
			customerRepository.save(new Customer(null, "Pierre", "pierre@mail.com"));
			customerRepository.findAll().forEach(System.out::println);
		};
	}
	
}

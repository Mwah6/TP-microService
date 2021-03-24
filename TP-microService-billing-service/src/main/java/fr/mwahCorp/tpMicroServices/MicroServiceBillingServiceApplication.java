package fr.mwahCorp.tpMicroServices;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import lombok.Data;

@SpringBootApplication
@EnableFeignClients
public class MicroServiceBillingServiceApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(MicroServiceBillingServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BillRepository billRepository, 
			ProductItemRepository productItemRepository,
			CustomerService customerService,
			InventoryService înventoryService) {
		return args -> {
			Customer c1 = customerService.findCustomerById(1L);
			System.out.println("********************");
			System.out.println("Id=" + c1.getId());
			System.out.println("Name=" + c1.getName());
			System.out.println("Email=" + c1.getEmail());
			System.out.println("********************");
			Bill bill1 = billRepository.save(new Bill(null, new Date(), 1L, null, null));
			
			PagedModel<Product> products = înventoryService.findAllProducts();
			products.getContent().forEach(p -> 
			productItemRepository.save(new ProductItem(null, p.getId(), null, p.getPrice(), 30, bill1)));
		};
	}
}

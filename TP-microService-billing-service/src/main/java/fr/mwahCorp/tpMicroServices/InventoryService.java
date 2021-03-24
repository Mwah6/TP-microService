package fr.mwahCorp.tpMicroServices;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="INVENTORY-SERVICE")
public interface InventoryService {
	@GetMapping("/products/{id}")
	public Product findProductById(@PathVariable(name = "id") Long id);
	@GetMapping(value = "/products")
	public PagedModel<Product> findAllProducts();

}

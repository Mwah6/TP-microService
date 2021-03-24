package fr.mwahCorp.tpMicroServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private ProductItemRepository repository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private InventoryService inventoryService;

	@GetMapping("/fullBill/{id}")
	public Bill getBill(@PathVariable(name="id") Long id) {
		Bill bill = billRepository.findById(id).get();
		bill.setCustomer(customerService.findCustomerById(bill.getCustomerID()));
		bill.getProdcutItems().forEach(pi->{
			pi.setProduct(inventoryService.findProductById(pi.getProductID()));
		});
		return bill;
	}
}

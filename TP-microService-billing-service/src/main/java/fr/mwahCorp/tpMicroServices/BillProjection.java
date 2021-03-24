package fr.mwahCorp.tpMicroServices;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="fullBill", types = Bill.class)
public interface BillProjection {
	public Long getId();
	public Date GetBillingDate();
	public Long getCustomerId();
	public Collection<ProductItem> getProductItems();
}

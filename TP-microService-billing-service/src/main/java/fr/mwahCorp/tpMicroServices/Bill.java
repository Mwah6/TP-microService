package fr.mwahCorp.tpMicroServices;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor @Data 
public class Bill {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date billingDate;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long customerID;
	@Transient
	private Customer customer;
	@OneToMany(mappedBy = "bill")
	private Collection<ProductItem> prodcutItems;

}

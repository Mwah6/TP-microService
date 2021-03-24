package fr.mwahCorp.tpMicroServices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ProductItem {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long productID;
	@Transient
	private Product product;
	private double price;
	private double quantity;
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //-> va ignorer la sérialisation en Json en "write"
	// -> JsonIgnore fonctionne aussi mais en écriture ET en lecture
	private Bill bill;
}

package br.com.harley.notification.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.harley.notification.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "orders")
@Getter
@Setter
public class Order implements Serializable {

	@Id
	private String id;
	
	private String waiter;
	
	@JsonProperty("table")
	private Integer tableNumber;

	private List<KitchenItem> kitchenItens;
	
	private List<BarItem> barItens;
	
	private Status statusBar;
	
	private Status statusKitchen;

}

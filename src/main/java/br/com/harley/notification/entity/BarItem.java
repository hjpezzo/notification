package br.com.harley.notification.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BarItem implements Serializable {

	@Id
	private Long id;
	
	private String name;
	
	private Integer quantity;
	
	private String note;
	
}

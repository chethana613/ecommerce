package com.hcl.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreProductDetailsResponsedto {
	private String storeName;
	private String location;
	private double price;
	private float rating;

}

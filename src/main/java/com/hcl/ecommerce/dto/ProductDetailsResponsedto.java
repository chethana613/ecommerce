package com.hcl.ecommerce.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailsResponsedto {
	private Integer productId;
	private String productName;
	private String category;
	List<StoreProductDetailsResponsedto> storeDetails;
}

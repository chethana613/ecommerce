package com.hcl.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductStoreRequestdto {
	private float productDiscount;
	private Integer storeId;
	private Integer productId;
}

package com.hcl.ecommerce.service;

import java.util.Optional;

import com.hcl.ecommerce.dto.ProductStoreRequestdto;
import com.hcl.ecommerce.dto.ProductStoreResponsedto;

public interface ProductStoreService{
	public Optional<ProductStoreResponsedto> addProductToStore(ProductStoreRequestdto productStoreRequestdto);
}

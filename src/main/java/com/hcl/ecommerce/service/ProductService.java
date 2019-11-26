package com.hcl.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.hcl.ecommerce.dto.ProductRequestdto;
import com.hcl.ecommerce.dto.ProductResponse;
import com.hcl.ecommerce.dto.ProductResponsedto;

public interface ProductService {
	public Optional<ProductResponsedto> addNewProduct(ProductRequestdto productRequestdto);
	public Optional<List<ProductResponse>> getAllAvailableProducts(String productName);
}

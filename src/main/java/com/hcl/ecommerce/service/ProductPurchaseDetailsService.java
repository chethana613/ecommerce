package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.ProductDetailsResponsedto;

public interface ProductPurchaseDetailsService {
	/**
	 * @author Chethana M
	 * @param productId
	 * @return ProductDetailsResponsedto
	 */
	public ProductDetailsResponsedto getProductPurchaseDetails(Integer productId);
}

package com.hcl.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.constants.UserConstants;
import com.hcl.ecommerce.dto.ProductStoreRequestdto;
import com.hcl.ecommerce.dto.ProductStoreResponsedto;
import com.hcl.ecommerce.exception.ProductException;
import com.hcl.ecommerce.service.ProductStoreService;

/**
 * 
 * @author Chethana
 * @Description This class is used to perform ECommerce Product&Store related
 *              Operations
 *
 */

@RestController
@RequestMapping("/product-store")
public class ProductStoreController {

	@Autowired
	ProductStoreService productStoreService;

	/**
	 * @author Chethana M
	 * @param productStoreRequestdto
	 * @return ProductStoreResponsedto
	 * @Description This method is used to add a new product to a store and discount
	 *              related details.
	 */
	@PostMapping
	public ResponseEntity<Optional<ProductStoreResponsedto>> addProductToStore(
			ProductStoreRequestdto productStoreRequestdto) {
		Optional<ProductStoreResponsedto> productStoreResponsedto = productStoreService
				.addProductToStore(productStoreRequestdto);
		if (!productStoreResponsedto.isPresent()) {
			throw new ProductException("Exception occured in adding a product to a store");
		}
		productStoreResponsedto.get().setMessage(UserConstants.SUCCESS);
		productStoreResponsedto.get().setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(productStoreResponsedto, HttpStatus.OK);
	}

}

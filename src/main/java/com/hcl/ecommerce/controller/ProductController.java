package com.hcl.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.constants.UserConstants;
import com.hcl.ecommerce.dto.ProductDetailsResponsedto;
import com.hcl.ecommerce.dto.ProductRequestdto;
import com.hcl.ecommerce.dto.ProductResponse;
import com.hcl.ecommerce.dto.ProductResponsedto;
import com.hcl.ecommerce.exception.ProductException;
import com.hcl.ecommerce.service.ProductPurchaseDetailsService;
import com.hcl.ecommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Chethana
 * @Description This class is used to perform ECommerce Product related
 *              Operations
 *
 */

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductPurchaseDetailsService productPurchaseDetailsService;

	/**
	 * @author Chethana M
	 * @Description This method lets the admin to add a new product into the
	 *              ecommerce application
	 * @param productRequestdto
	 * @return ProductResponsedto
	 * @exception PRODUCT_EXISTS
	 */
	@PostMapping("/product")
	public ResponseEntity<Optional<ProductResponsedto>> addNewProduct(
			@RequestBody ProductRequestdto productRequestdto) {
		log.info("Entering into addNewProduct method of ProductController");
		Optional<ProductResponsedto> productResponsedto = productService.addNewProduct(productRequestdto);
		if (!productResponsedto.isPresent()) {
			throw new ProductException("Exception occured in adding a product");
		}
		productResponsedto.get().setMessage(UserConstants.SUCCESS);
		productResponsedto.get().setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(productResponsedto, HttpStatus.OK);
	}

	/**
	 * @author Chethana M
	 * @Description This method gets all the product details available in the
	 *              ecommerce application that matches with the requested product
	 *              name pattern
	 * @param productName
	 * @return List of ProductResponse
	 * @exception "No Relevant products available"
	 * 
	 */
	@GetMapping("/product/{productName}")
	public ResponseEntity<List<ProductResponse>> getAllAvailableProducts(String productName) {
		Optional<List<ProductResponse>> productResponse = productService.getAllAvailableProducts(productName);
		if (!productResponse.isPresent()) {
			throw new ProductException("No Relevant Products Available");
		}
		return new ResponseEntity<>(productResponse.get(), HttpStatus.OK);
	}
	
	/**
	 * @author Chethana M
	 * @Description This Method displays the product information along with the
	 *              store details in which the product is available and also the
	 *              rating of the store 
	 * @param productId
	 * @return
	 */
	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDetailsResponsedto> getProductDetails(Integer productId) {
		ProductDetailsResponsedto productDetailsResponsedto=productPurchaseDetailsService.getProductPurchaseDetails(productId);
		return new ResponseEntity<>(productDetailsResponsedto, HttpStatus.OK);
	}

}

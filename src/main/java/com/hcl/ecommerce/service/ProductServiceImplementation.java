package com.hcl.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.constants.ProductConstants;
import com.hcl.ecommerce.dto.ProductRequestdto;
import com.hcl.ecommerce.dto.ProductResponse;
import com.hcl.ecommerce.dto.ProductResponsedto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductException;
import com.hcl.ecommerce.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Chethana M
 * @Description This class is used to perform the product related operations
 *
 */
@Service
@Slf4j
public class ProductServiceImplementation implements ProductService {

	@Autowired
	ProductRepository productRepository;

	/**
	 * 
	 * @Description This method lets the admin to add a new product into the
	 *              ecommerce application
	 * @param productRequestdto
	 * @return ProductResponsedto
	 * @exception PRODUCT_EXISTS
	 * 
	 */
	public Optional<ProductResponsedto> addNewProduct(ProductRequestdto productRequestdto) {
		log.info("Entering into addNewProduct of ProductServiceImplementation");
		Optional<Product> productDetails = productRepository
				.findByProductNameAndCategory(productRequestdto.getProductName(), productRequestdto.getCategory());
		if (productDetails.isPresent()) {
			log.error("Exception in addNewProduct of ProductServiceImplementation:" + ProductConstants.PRODUCT_EXISTS);
			throw new ProductException(ProductConstants.PRODUCT_EXISTS);
		}
		Product product = new Product();
		product.setCategory(productRequestdto.getCategory());
		product.setPrice(productRequestdto.getPrice());
		product.setProductName(productRequestdto.getProductName());
		productRepository.save(product);
		ProductResponsedto productResponsedto = new ProductResponsedto();
		return Optional.of(productResponsedto);

	}

	/**
	 * @Description This method gets all the product details available in the
	 *              ecommerce application that matches with the requested product
	 *              name pattern
	 * @param productName
	 * @return ProductResponse
	 */
	public Optional<List<ProductResponse>> getAllAvailableProducts(String productName) {
		log.info("Entering into getAllAvailableProducts of ProductServiceImplementation");
		List<Product> productDetails = productRepository.findByProductNameContaining(productName);
		List<ProductResponse> productResponseList = new ArrayList<>();
		for (Product product : productDetails) {
			ProductResponse productResponse = new ProductResponse();
			BeanUtils.copyProperties(product, productResponse);
			productResponseList.add(productResponse);
		}

		return Optional.of(productResponseList);

	}
}

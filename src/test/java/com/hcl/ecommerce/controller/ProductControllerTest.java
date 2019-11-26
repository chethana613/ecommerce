package com.hcl.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.dto.ProductDetailsResponsedto;
import com.hcl.ecommerce.dto.ProductRequestdto;
import com.hcl.ecommerce.dto.ProductResponse;
import com.hcl.ecommerce.dto.ProductResponsedto;
import com.hcl.ecommerce.dto.StoreProductDetailsResponsedto;
import com.hcl.ecommerce.exception.ProductException;
import com.hcl.ecommerce.service.ProductPurchaseDetailsImplementation;
import com.hcl.ecommerce.service.ProductServiceImplementation;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductControllerTest {
	@Mock
	ProductServiceImplementation productServiceImplementation;
	@Mock
	ProductPurchaseDetailsImplementation productPurchaseDetailsImplementation;

	@InjectMocks
	ProductController productController;

	ProductRequestdto productRequestdto;
	ProductResponsedto productResponsedto;
	List<ProductResponse> ProductResponseList = new ArrayList<>();
	ProductResponse productResponse = null;
	ProductDetailsResponsedto productDetailsResponsedto;
	List<StoreProductDetailsResponsedto> storeProductDetailsResponsedtoList = new ArrayList<>();
	StoreProductDetailsResponsedto storeProductDetailsResponsedto = null;

	@Before
	public void setUp() {
		productRequestdto = new ProductRequestdto();
		productResponsedto = new ProductResponsedto();
		productRequestdto.setCategory("medicine");
		productRequestdto.setPrice(50);
		productRequestdto.setProductName("asprin");

		productResponsedto.setMessage("success");
		productResponsedto.setStatusCode(200);
		productResponse = new ProductResponse();
		productResponse.setCategory("medicine");
		productResponse.setProductName("asprin");
		ProductResponseList.add(productResponse);

		storeProductDetailsResponsedto = new StoreProductDetailsResponsedto();
		storeProductDetailsResponsedto.setLocation("bangalore");
		storeProductDetailsResponsedto.setPrice(600);
		storeProductDetailsResponsedto.setRating(7);
		storeProductDetailsResponsedto.setStoreName("fasttrack");
		storeProductDetailsResponsedtoList.add(storeProductDetailsResponsedto);

		productDetailsResponsedto = new ProductDetailsResponsedto();
		productDetailsResponsedto.setCategory("medicine");
		productDetailsResponsedto.setProductId(1);
		productDetailsResponsedto.setProductName("asprin");
		productDetailsResponsedto.setStoreDetails(storeProductDetailsResponsedtoList);

	}

	@Test
	public void testaddNewProduct() {
		Mockito.when(productServiceImplementation.addNewProduct(productRequestdto))
				.thenReturn(Optional.of(productResponsedto));
		ResponseEntity<Optional<ProductResponsedto>> productResponsedto = productController
				.addNewProduct(productRequestdto);
		Assert.assertNotNull(productResponsedto);
	}

	@Test(expected = ProductException.class)
	public void testaddNewProductNegative() {
		Mockito.when(productServiceImplementation.addNewProduct(productRequestdto))
				.thenReturn(Optional.ofNullable(null));
		productController.addNewProduct(productRequestdto);
	}

	@Test
	public void testGetAllAvailableProducts() {
		Mockito.when(productServiceImplementation.getAllAvailableProducts(Mockito.anyString()))
				.thenReturn(Optional.of(ProductResponseList));
		ResponseEntity<List<ProductResponse>> productResponse = productController.getAllAvailableProducts("asprin");
		Assert.assertNotNull(productResponse);
	}

	@Test(expected = ProductException.class)
	public void testGetAllAvailableProductsNegative() {
		Mockito.when(productServiceImplementation.getAllAvailableProducts(Mockito.anyString()))
				.thenReturn(Optional.ofNullable(null));
		productController.getAllAvailableProducts("asprin");
	}

	@Test
	public void testGetProductDetails() {
		Mockito.when(productPurchaseDetailsImplementation.getProductPurchaseDetails(1))
				.thenReturn(productDetailsResponsedto);
		ResponseEntity<ProductDetailsResponsedto> productDetailsEntityResponsedto = productController
				.getProductDetails(1);
		Assert.assertNotNull(productDetailsEntityResponsedto);
	}

}

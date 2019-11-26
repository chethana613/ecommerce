package com.hcl.ecommerce.controller;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.constants.UserConstants;
import com.hcl.ecommerce.dto.ProductStoreRequestdto;
import com.hcl.ecommerce.dto.ProductStoreResponsedto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.exception.ProductException;
import com.hcl.ecommerce.service.ProductStoreServiceImplementation;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductStoreControllerTest {
	
	@InjectMocks
	ProductStoreController productStoreController;
	
	@Mock
	ProductStoreServiceImplementation productStoreServiceImplementation;
	
	ProductStoreRequestdto productStoreRequestdto;
	ProductStoreRequestdto productStoreRequestdto1;
	ProductStoreResponsedto productStoreResponsedto;
	Product product= null;
	Store store= null;
	
	@Before
	public void setUp() {
		productStoreResponsedto= new ProductStoreResponsedto();
		productStoreRequestdto= new ProductStoreRequestdto();
		productStoreRequestdto1= new ProductStoreRequestdto();
		product= new Product();
		store= new Store();
		
		productStoreRequestdto.setProductDiscount(10);
		productStoreRequestdto.setProductId(1);
		productStoreRequestdto.setStoreId(1);
		
		productStoreRequestdto1.setProductDiscount(10);
		productStoreRequestdto1.setProductId(2);
		productStoreRequestdto1.setStoreId(1);
		
		productStoreResponsedto.setMessage(UserConstants.SUCCESS);
		productStoreResponsedto.setStatusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testAddProductToStore() {
		Mockito.when(productStoreServiceImplementation.addProductToStore(Mockito.any())).thenReturn(Optional.of(productStoreResponsedto));
		ResponseEntity<Optional<ProductStoreResponsedto>> productResponsedto = productStoreController.addProductToStore(productStoreRequestdto);
		Assert.assertNotNull(productResponsedto);
	}
	
	@Test(expected=ProductException.class)
	public void testAddProductToStoreNegative() {
		Mockito.when(productStoreServiceImplementation.addProductToStore(productStoreRequestdto1)).thenReturn(Optional.of(productStoreResponsedto));
		ResponseEntity<Optional<ProductStoreResponsedto>> productResponsedto = productStoreController.addProductToStore(productStoreRequestdto);
		Assert.assertNotNull(productResponsedto);
	}

}

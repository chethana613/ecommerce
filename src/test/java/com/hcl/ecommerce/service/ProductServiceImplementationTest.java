package com.hcl.ecommerce.service;

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

import com.hcl.ecommerce.dto.ProductRequestdto;
import com.hcl.ecommerce.dto.ProductResponse;
import com.hcl.ecommerce.dto.ProductResponsedto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductException;
import com.hcl.ecommerce.repository.ProductRepository;

/**
 * 
 * @author Chethana M
 * @Description This class is used for to do test operations for product service
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceImplementationTest {
	
	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	ProductServiceImplementation productServiceImplementation;
	
	Product product=null;
	ProductRequestdto productRequestdto=null;
	List<Product> productDetails= new ArrayList<>();
	@Before
	public void setup() {
		product= new Product();
		product.setCategory("medicine");
		product.setPrice(50);
		product.setProductId(1);
		product.setProductName("vicks");
		productRequestdto= new ProductRequestdto();
		productRequestdto.setCategory("medicine");
		productRequestdto.setPrice(50);
		productRequestdto.setProductName("vicks");
		productDetails.add(product);
	}
	
	@Test
	public void testAddNewProduct() {
		
		Mockito.when(productRepository.findByProductNameAndCategory("a", "a")).thenReturn(Optional.of(product));
		Optional<ProductResponsedto> productResponsedto=productServiceImplementation.addNewProduct(productRequestdto);
		Assert.assertNotNull(productResponsedto);
	}
	
	@Test(expected=ProductException.class)
	public void testAddNewProductNegative() {	
		Mockito.when(productRepository.findByProductNameAndCategory(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(product));
		Optional<ProductResponsedto> productResponsedto=productServiceImplementation.addNewProduct(productRequestdto);
		Assert.assertNotNull(productResponsedto);
	}
	@Test
	public void testGetAllAvailableProducts() {
		Mockito.when(productRepository.findByProductNameContaining(Mockito.anyString())).thenReturn(productDetails);
		Optional<List<ProductResponse>> productResponseList=productServiceImplementation.getAllAvailableProducts(productRequestdto.getProductName());
		Assert.assertNotNull(productResponseList);
	}

}

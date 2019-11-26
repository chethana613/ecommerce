package com.hcl.ecommerce.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ecommerce.dto.ProductStoreRequestdto;
import com.hcl.ecommerce.dto.ProductStoreResponsedto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductStore;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.exception.ProductException;
import com.hcl.ecommerce.exception.StoreException;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.ProductStoreDetailsRepository;
import com.hcl.ecommerce.repository.StoreRepository;

/**
 * 
 * @author Chethana M
 * @Description This class is used for to do test operations for product-store service
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductStoreServiceImplementationTest {
	
	@Mock
	ProductRepository productRepository;
	@Mock
	StoreRepository storeRepository;
	@Mock
	ProductStoreDetailsRepository productStoreDetailsRepository;
	
	@InjectMocks
	ProductStoreServiceImplementation productStoreServiceImplementation;
	
	Store store=null;
	Product product= null;
	Store storePresent=null;
	Product productPresent= null;
	ProductStoreRequestdto productStoreRequestdto=null;
	ProductStore productStore= null;
	
	@Before
	public void setup() {
		store= new Store();
		product= new Product();
		product.setCategory("medicine");
		product.setPrice(100);
		product.setProductId(6);
		product.setProductName("asprin");
		
		store.setLocation("mumbai");
		store.setStoreId(1);
		store.setStoreName("fastrack");
		
		productStoreRequestdto= new ProductStoreRequestdto();
		productStoreRequestdto.setProductDiscount(10);
		productStoreRequestdto.setProductId(4);
		productStoreRequestdto.setStoreId(4);
		
		productStore= new ProductStore();
		productStore.setProductDiscount(15);
		productStore.setProductId(product);
		productStore.setStoreId(store);
	}
	
	@Test(expected=ProductException.class)
	public void testAddInvalidProductToStoreNegative() {
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
		Mockito.when(storeRepository.findById(1)).thenReturn(Optional.of(store));
		productStoreServiceImplementation.addProductToStore(productStoreRequestdto);
	}
	
	@Test(expected=StoreException.class)
	public void testAddProductToInvalidStoreNegative() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(product));
		Mockito.when(storeRepository.findById(1)).thenReturn(Optional.of(store));
		productStoreServiceImplementation.addProductToStore(productStoreRequestdto);
	}
	
	@Test(expected=ProductException.class)
	public void testAddProductToStoreNegative() {
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(product));
		Mockito.when(storeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(store));
		Mockito.when(productStoreDetailsRepository.findByProductIdAndStoreId(product,store)).thenReturn(Optional.of(productStore));
		Optional<ProductStoreResponsedto> productStoreResponsedto=productStoreServiceImplementation.addProductToStore(productStoreRequestdto);
		Assert.assertNotNull(productStoreResponsedto);
	}
	
	@Test
	public void testAddProductToStore() {
		storePresent= new Store();
		productPresent= new Product();
		
		productPresent.setCategory("medicine");
		productPresent.setPrice(100);
		productPresent.setProductId(66);
		productPresent.setProductName("asprin");
		
		storePresent.setLocation("mumbai");
		storePresent.setStoreId(11);
		storePresent.setStoreName("fastrack");
		
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(product));
		Mockito.when(storeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(store));
		
		Mockito.when(productStoreDetailsRepository.findByProductIdAndStoreId(productPresent,storePresent)).thenReturn(Optional.of(productStore));
		Optional<ProductStoreResponsedto> productStoreResponsedto=productStoreServiceImplementation.addProductToStore(productStoreRequestdto);
		Assert.assertNotNull(productStoreResponsedto);
	}

}

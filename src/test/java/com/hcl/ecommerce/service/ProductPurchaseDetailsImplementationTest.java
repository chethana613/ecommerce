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

import com.hcl.ecommerce.dto.ProductDetailsResponsedto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductStore;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.entity.UserStoreRating;
import com.hcl.ecommerce.exception.ProductException;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.ProductStoreDetailsRepository;
import com.hcl.ecommerce.repository.StoreRepository;
import com.hcl.ecommerce.repository.UserStoreRatingRepository;

/**
 * 
 * @author Chethana M
 * @Description This class is used for to do test operations for product purchase details service
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductPurchaseDetailsImplementationTest {
	
	@Mock
	ProductStoreDetailsRepository productStoreDetailsRepository;
	@Mock
	ProductRepository productRepository;
	@Mock
	StoreRepository storeRepository;
	@Mock
	UserStoreRatingRepository userStoreRatingRepository;
	
	@InjectMocks
	ProductPurchaseDetailsImplementation productPurchaseDetailsImplementation;
	
	Product product=null;
	List<ProductStore> productStoreList= new ArrayList<>();
	List<UserStoreRating> userStoreRatingList= new ArrayList<>();
	ProductStore productStore= new ProductStore();
	UserStoreRating userStoreRating= new UserStoreRating();
	Store store = new Store();
	Store store1=null;
	@Before
	public void setup() {
		product= new Product();
		product.setCategory("medicine");
		product.setPrice(50);
		product.setProductId(1);
		product.setProductName("vicks");
		store.setLocation("Bihar");
		store.setStoreId(1);
		store.setStoreName("Bathin");
		productStore.setProductDiscount(10);
		productStore.setProductStoreId(1);
		productStore.setStoreId(store);
		productStore.setProductId(product);
		productStoreList.add(productStore);
		userStoreRating.setRating(5);
		userStoreRating.setStoreId(store);
		userStoreRatingList.add(userStoreRating);
		
	}
	@Test(expected=ProductException.class)
	public void testGetProductPurchaseDetailsNegative() {
		Mockito.when(productRepository.findById(4)).thenReturn(Optional.of(product));
		productPurchaseDetailsImplementation.getProductPurchaseDetails(5);
	}
	
	@Test
	public void testGetProductPurchaseDetailsRating() {
		Mockito.when(productRepository.findById(4)).thenReturn(Optional.of(product));
		Mockito.when(productStoreDetailsRepository.findByProductId(product)).thenReturn(productStoreList);
		Mockito.when(storeRepository.findById(1)).thenReturn(Optional.of(store));
		Mockito.when(userStoreRatingRepository.findByStoreId(store)).thenReturn(userStoreRatingList);
		ProductDetailsResponsedto productDetailsResponsedto=productPurchaseDetailsImplementation.getProductPurchaseDetails(4);
		Assert.assertNotNull(productDetailsResponsedto);
	}
	
	@Test
	public void testGetProductPurchaseDetailsNoRating() {
		store1= new Store();
		userStoreRating.setRating(5);
		userStoreRating.setStoreId(store);
		userStoreRatingList.add(userStoreRating);
		Mockito.when(productRepository.findById(4)).thenReturn(Optional.of(product));
		Mockito.when(productStoreDetailsRepository.findByProductId(product)).thenReturn(productStoreList);
		Mockito.when(storeRepository.findById(1)).thenReturn(Optional.of(store));
		Mockito.when(userStoreRatingRepository.findByStoreId(store1)).thenReturn(userStoreRatingList);
		ProductDetailsResponsedto productDetailsResponsedto=productPurchaseDetailsImplementation.getProductPurchaseDetails(4);
		Assert.assertNotNull(productDetailsResponsedto);
	}

}

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
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.dto.StoreRequestdto;
import com.hcl.ecommerce.dto.StoreResponsedto;
import com.hcl.ecommerce.exception.StoreException;
import com.hcl.ecommerce.service.StoreServiceImplementation;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StoreControllerTest {

	@Mock
	StoreServiceImplementation storeServiceImplementation;
	
	@InjectMocks
	StoreController storeController;
	
	StoreResponsedto storeResponsedto;
	StoreRequestdto storeRequestdto;
	
	@Before
	public void setUp() {
		storeResponsedto= new StoreResponsedto();
		storeRequestdto= new StoreRequestdto();
		storeRequestdto.setLocation("bellandur");
		storeRequestdto.setStoreName("aristo");
		storeResponsedto.setMessage("success");
		storeResponsedto.setStatusCode(200);		
	}
	
	@Test
	public void testAddNewStore() {
		Mockito.when(storeServiceImplementation.addNewStore(storeRequestdto)).thenReturn(Optional.of(storeResponsedto));
		ResponseEntity<Optional<StoreResponsedto>> storeResponsedto=storeController.addNewStore(storeRequestdto);
		Assert.assertNotNull(storeResponsedto);
	}
	
	@Test(expected=StoreException.class)
	public void testAddNewStoreNegative() {
		Mockito.when(storeServiceImplementation.addNewStore(storeRequestdto)).thenReturn(Optional.ofNullable(null));
		storeController.addNewStore(storeRequestdto);
	}
}


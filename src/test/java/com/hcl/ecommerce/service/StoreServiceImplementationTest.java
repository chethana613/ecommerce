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

import com.hcl.ecommerce.dto.StoreRequestdto;
import com.hcl.ecommerce.dto.StoreResponsedto;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.exception.StoreException;
import com.hcl.ecommerce.repository.StoreRepository;

/**
 * 
 * @author Chethana M
 * @Description This class is used for to do test operation for store service
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class StoreServiceImplementationTest {
	
	@Mock
	StoreRepository storeRepository;
	
	@InjectMocks
	StoreServiceImplementation storeServiceImplementation;

	Store store=null;
	StoreRequestdto storeRequestdto=null;
	@Before
	public void setup() {
		store= new Store();
		store.setStoreId(1);
		store.setStoreName("Fast Dealers");
		store.setLocation("Bellandur");
		storeRequestdto= new StoreRequestdto();
		storeRequestdto.setLocation("Bellandur");
		storeRequestdto.setStoreName("Fast Dealers");
	}
	
	@Test
	public void testAddNewStore() {

		Mockito.when(storeRepository.findByStoreNameAndLocation("a","a")).thenReturn(Optional.of(store));
		Optional<StoreResponsedto> storeResponsedto=storeServiceImplementation.addNewStore(storeRequestdto);
		Assert.assertNotNull(storeResponsedto);
	}
	
	@Test(expected=StoreException.class)
	public void testAddNewStoreNegative() {
		Mockito.when(storeRepository.findByStoreNameAndLocation(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(store));	
		storeServiceImplementation.addNewStore(storeRequestdto);
	}
}

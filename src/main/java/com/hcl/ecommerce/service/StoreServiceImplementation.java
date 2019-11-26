package com.hcl.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.constants.StoreConstants;
import com.hcl.ecommerce.dto.StoreRequestdto;
import com.hcl.ecommerce.dto.StoreResponsedto;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.exception.StoreException;
import com.hcl.ecommerce.repository.StoreRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Chethana M
 * @Description This class is used to perform the Store related operations
 *
 */
@Service
@Slf4j
public class StoreServiceImplementation implements StoreService {
	@Autowired
	StoreRepository storeRepository;

	/**
	 * 
	 * @Description This method lets the admin to add a new Store to sell product
	 *              into the ecommerce application
	 * @param storeRequestdto
	 * @return StoreResponsedto
	 * @exception STORE_EXISTS
	 * 
	 */
	public Optional<StoreResponsedto> addNewStore(StoreRequestdto storeRequestdto) {
		log.info("Entering into addNewStore of StoreServiceImplementation");
		Optional<Store> storeDetails = storeRepository.findByStoreNameAndLocation(storeRequestdto.getStoreName(),
				storeRequestdto.getLocation());
		if (storeDetails.isPresent()) {
			log.error("Exception in addNewStore of StoreServiceImplementation:" + StoreConstants.STORE_EXISTS);
			throw new StoreException(StoreConstants.STORE_EXISTS);
		}
		Store store = new Store();
		store.setLocation(storeRequestdto.getLocation());
		store.setStoreName(storeRequestdto.getStoreName());
		storeRepository.save(store);
		StoreResponsedto storeResponsedto = new StoreResponsedto();
		return Optional.of(storeResponsedto);

	}
}

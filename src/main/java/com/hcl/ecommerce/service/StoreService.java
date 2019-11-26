package com.hcl.ecommerce.service;

import java.util.Optional;

import com.hcl.ecommerce.dto.StoreRequestdto;
import com.hcl.ecommerce.dto.StoreResponsedto;

/**
 *@Description This Interface is used to declare the abstract functionality
 *              methods that are further going to be implemented in the classes
 *              which implements this interface
 * @author Chethana M
 *
 */
public interface StoreService {
	/**
	 * 
	 * @param storeRequestdto
	 * @return StoreResponsedto
	 */
	public Optional<StoreResponsedto> addNewStore(StoreRequestdto storeRequestdto);
}

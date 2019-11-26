package com.hcl.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.constants.UserConstants;
import com.hcl.ecommerce.dto.StoreRequestdto;
import com.hcl.ecommerce.dto.StoreResponsedto;
import com.hcl.ecommerce.exception.StoreException;
import com.hcl.ecommerce.service.StoreService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Chethana
 * @Description This class is used to perform ECommerce Store related Operations
 *
 */
@RestController
@RequestMapping("/stores")
@Slf4j
public class StoreController {
	@Autowired
	StoreService storeService;
	
	/**
	 * @Description This method lets the admin to add a new Store into the
	 *              ecommerce application
	 * @param storeRequestdto
	 * @return StoreResponsedto
	 * @exception STORE_EXISTS
	 */
	@PostMapping("/store")
	public ResponseEntity<Optional<StoreResponsedto>> addNewStore(@RequestBody StoreRequestdto storeRequestdto){
		log.info("Entering into addNewStore method of StoreController");
		Optional<StoreResponsedto> storeResponsedto=storeService.addNewStore(storeRequestdto);
		if(!storeResponsedto.isPresent()) {
			throw new StoreException("Exception occured in adding a Store");
		}
		storeResponsedto.get().setMessage(UserConstants.SUCCESS);
		storeResponsedto.get().setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(storeResponsedto, HttpStatus.OK);
	}
}

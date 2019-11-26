package com.hcl.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Description This class is used to add a new product to a store
 * @author Chethana M
 * 
 *
 */
@Service
public class ProductStoreServiceImplementation implements ProductStoreService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	ProductStoreDetailsRepository productStoreDetailsRepository;
	
	/**
	 * @author Chethana M
	 * @Description This class is used to add a new valid product to a valid store
	 * @param productStoreRequestdto
	 * @return ProductStoreResponsedto
	 * 
	 */
	public Optional<ProductStoreResponsedto> addProductToStore(ProductStoreRequestdto productStoreRequestdto){
		Optional<Product> product=productRepository.findById(productStoreRequestdto.getProductId());
		Optional<Store> store=storeRepository.findById(productStoreRequestdto.getStoreId());
		
		if(!product.isPresent()) {
			throw new ProductException("Invalid Product");
		}
		else if(!store.isPresent()) {
			throw new StoreException("Invalid Store");
		}
		Optional<ProductStore> productStoreOptional=productStoreDetailsRepository.findByProductIdAndStoreId(product.get(), store.get());
		if(productStoreOptional.isPresent()) {
			throw new ProductException("Product is already available in the store");
		}
		ProductStore productStore= new ProductStore();
		productStore.setProductDiscount(10);
		productStore.setProductId(product.get());
		productStore.setStoreId(store.get());
		productStoreDetailsRepository.save(productStore);
		ProductStoreResponsedto productStoreResponsedto= new ProductStoreResponsedto();
		return Optional.of(productStoreResponsedto);		
	}
}

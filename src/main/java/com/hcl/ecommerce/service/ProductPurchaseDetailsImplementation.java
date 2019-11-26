package com.hcl.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.ProductDetailsResponsedto;
import com.hcl.ecommerce.dto.StoreProductDetailsResponsedto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductStore;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.entity.UserStoreRating;
import com.hcl.ecommerce.exception.ProductException;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.ProductStoreDetailsRepository;
import com.hcl.ecommerce.repository.StoreRepository;
import com.hcl.ecommerce.repository.UserStoreRatingRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Chethana M
 * @Description This class is used to Display the selected product related
 *              information
 *
 */
@Service
@Slf4j
public class ProductPurchaseDetailsImplementation implements ProductPurchaseDetailsService{

	@Autowired
	ProductStoreDetailsRepository productStoreDetailsRepository;

	@Autowired
	UserStoreRatingRepository userStoreRatingRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	StoreRepository storeRepository;

	/**
	 * @author Chethana M
	 * @Description This Method displays the product information along with the
	 *              store details in which the product is available and also the
	 *              rating of the store
	 * 
	 * @param productId
	 * @return
	 */

	public ProductDetailsResponsedto getProductPurchaseDetails(Integer productId) {
		log.info("Entering into getProductPurchaseDetails of ProductPurchaseDetailsImplementation");
		Optional<Product> product = productRepository.findById(productId);
		if (!product.isPresent()) {
			throw new ProductException("Invalid Product");
		}
		List<ProductStore> productStoreList = productStoreDetailsRepository.findByProductId(product.get());
		List<Integer> storeIdList = new ArrayList<>();
		List<Float> discountList = new ArrayList<>();
		/**
		 * @Description The following block of code retrieves all the available store ids of the product along with the product discount details in the product.
		 */
		productStoreList.forEach(list -> {
			Store store= list.getStoreId();
			storeIdList.add(store.getStoreId());
			discountList.add(list.getProductDiscount());
		});
		
		List<StoreProductDetailsResponsedto> storeDetailsList = new ArrayList<>();
		int discountIndex=0;
		for (Integer storeId : storeIdList) {
			Optional<Store> store = storeRepository.findById(storeId);
			if (store.isPresent()) {
				StoreProductDetailsResponsedto storeProductDetailsResponsedto = new StoreProductDetailsResponsedto();
				storeProductDetailsResponsedto.setLocation(store.get().getLocation());
				storeProductDetailsResponsedto.setStoreName(store.get().getStoreName());
				storeProductDetailsResponsedto.setPrice(product.get().getPrice() - (discountList.get(discountIndex)*0.1));
				List<UserStoreRating> userStoreRatingList = userStoreRatingRepository.findByStoreId(store.get());
				/**
				 * @Description If the user description for the store is present, average is returned else no rating is given.
				 */
				if (userStoreRatingList.isEmpty()) {
					storeProductDetailsResponsedto.setRating(0);
				} else {
					float ratingAverage = 0;
					for (UserStoreRating userStoreRating : userStoreRatingList) {
						ratingAverage += userStoreRating.getRating();
					}
					ratingAverage = (ratingAverage / userStoreRatingList.size());
					storeProductDetailsResponsedto.setRating(ratingAverage);
				}

				storeDetailsList.add(storeProductDetailsResponsedto);
			}
			discountIndex++;
		}

		ProductDetailsResponsedto productDetailsResponsedto = new ProductDetailsResponsedto();
		productDetailsResponsedto.setCategory(product.get().getCategory());
		productDetailsResponsedto.setProductId(productId);
		productDetailsResponsedto.setProductName(product.get().getProductName());
		productDetailsResponsedto.setStoreDetails(storeDetailsList);

		return productDetailsResponsedto;

	}

}

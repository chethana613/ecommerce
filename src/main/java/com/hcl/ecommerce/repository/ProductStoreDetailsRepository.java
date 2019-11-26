package com.hcl.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductStore;
import com.hcl.ecommerce.entity.Store;

@Repository
public interface ProductStoreDetailsRepository extends JpaRepository<ProductStore, Integer> {
	List<ProductStore> findByProductId(Product product);
	Optional<ProductStore> findByProductIdAndStoreId(Product product,Store store);
}

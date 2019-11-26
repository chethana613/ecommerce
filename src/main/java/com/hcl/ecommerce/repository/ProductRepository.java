package com.hcl.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Product;

/**
 * 
 * @author Chethana M
 * @Description This interface is used as a repository for Entity 'Product'.This
 *              Interface declares derived/native queries that are needed to be
 *              fetched from database.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByProductNameAndCategory(String productName,String category);
	List<Product> findByProductNameContaining(String productName);
}

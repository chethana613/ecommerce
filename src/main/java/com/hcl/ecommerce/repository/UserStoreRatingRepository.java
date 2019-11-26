package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.entity.UserStoreRating;

@Repository
public interface UserStoreRatingRepository extends JpaRepository<UserStoreRating, Integer>{

	List<UserStoreRating> findByStoreId(Store store);
}

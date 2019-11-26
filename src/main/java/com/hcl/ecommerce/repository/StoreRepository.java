package com.hcl.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Store;

/**
 * 
 * @author Chethana M
 * @Description This interface is used as a repository for Entity 'Store'.This
 *              Interface declares derived/native queries that are needed to be
 *              fetched from database.
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
	Optional<Store> findByStoreNameAndLocation(String storeName,String location);
}

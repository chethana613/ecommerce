package com.hcl.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.User;

/**
 * 
 * @author Chethana M
 * @Description This interface is used as a repository for Entity 'User'.This
 *              Interface declares derived/native queries that are needed to be
 *              fetched from database.
 *
 */

@Repository
public interface LoginRepository extends JpaRepository<User, Integer>{
	
	/**
	 * 
	 * @param userMail
	 * @param pass
	 * @return
	 */
	Optional<User> findByUserMailAndPass(String userMail, String pass);
}

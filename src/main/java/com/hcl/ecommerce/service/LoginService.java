package com.hcl.ecommerce.service;

import java.util.Optional;

import com.hcl.ecommerce.dto.LoginRequestdto;
import com.hcl.ecommerce.dto.LoginResponsedto;

/**
 * 
 * @author Chethana M
 * @Description This Interface is used to declare the abstract functionality
 *              methods that are further going to be implemented in the classes
 *              which implements this interface
 *
 */
public interface LoginService {

	/**
	 * @param loginRequestdto
	 * @return Optional<LoginResponsedto>
	 */

	Optional<LoginResponsedto> login(LoginRequestdto loginRequestdto);

}

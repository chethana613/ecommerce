package com.hcl.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.constants.UserConstants;
import com.hcl.ecommerce.dto.LoginRequestdto;
import com.hcl.ecommerce.dto.LoginResponsedto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.UserException;
import com.hcl.ecommerce.repository.LoginRepository;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author Chethana M
 * @Description This class is used to provide Login Functionality implementation
 *
 */

@Service
@Slf4j
public class LoginServiceImplementation implements LoginService {

	@Autowired
	LoginRepository loginRepository;
	
	/**
	 * @Description This method is used for user to login with valid credentials
	 * @param loginRequestdto
	 * @return LoginResponsedto
	 * @exception INVALID_LOGIN
	 */
	
	public Optional<LoginResponsedto> login(LoginRequestdto loginRequestdto){
		log.info("Enter into login() of LoginServiceImplementation");
		Optional<User> userDetails=loginRepository.findByUserMailAndPass(loginRequestdto.getUserMail(), loginRequestdto.getPassword());
		if(!userDetails.isPresent()) {
			log.error("Exception occured in login of LoginServiceImplementation:"+UserConstants.INVALID_LOGIN);
			throw new UserException(UserConstants.INVALID_LOGIN);
		}
		LoginResponsedto loginResponsedto= new LoginResponsedto();
		return Optional.of(loginResponsedto);
	}

}

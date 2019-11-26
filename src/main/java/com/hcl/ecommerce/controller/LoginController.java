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
import com.hcl.ecommerce.dto.LoginRequestdto;
import com.hcl.ecommerce.dto.LoginResponsedto;
import com.hcl.ecommerce.exception.UserException;
import com.hcl.ecommerce.service.LoginService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Chethana
 * @Description This class is used to perform Login Operation for the User to login into Ecommerce application
 *
 */

@RestController
@RequestMapping("/users")
@Slf4j
public class LoginController {

	
	@Autowired
	LoginService loginService;
	
	/**
	 * 
	 * @Description This method is used for User/admin login
	 * @param loginRequestdto
	 * @return LoginResponsedto
	 * @exception INVALID_LOGIN
	 * 
	 */
	@PostMapping
	public ResponseEntity<Optional<LoginResponsedto>> login(@RequestBody LoginRequestdto loginRequestdto){
		log.info("Entering into login method of LoginController");
		Optional<LoginResponsedto> loginResponsedto= loginService.login(loginRequestdto);
		if(!loginResponsedto.isPresent()) {
			throw new UserException(UserConstants.INVALID_LOGIN);
		}
		loginResponsedto.get().setMessage(UserConstants.SUCCESS);
		loginResponsedto.get().setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(loginResponsedto, HttpStatus.OK);
		
	}
}

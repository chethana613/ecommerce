package com.hcl.ecommerce.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.hcl.ecommerce.dto.LoginRequestdto;
import com.hcl.ecommerce.dto.LoginResponsedto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.UserException;
import com.hcl.ecommerce.repository.LoginRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Chethana M
 * @Description This class is used for to do test operation for login service
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)
@Slf4j
public class LoginServiceImplementationTest {
	@InjectMocks
	LoginServiceImplementation loginServiceImplementation;

	@Mock
	LoginRepository loginRepository;
	
	LoginResponsedto loginResponsedto;
	LoginRequestdto loginRequestdto;
	User user;

	public LoginResponsedto getLoginResponse() {
		loginResponsedto = new LoginResponsedto();
		loginResponsedto.setMessage("Login Success");
		loginResponsedto.setStatusCode(HttpStatus.OK.value());
		return loginResponsedto;
	}

	public LoginRequestdto getLoginRequest() {
		loginRequestdto = new LoginRequestdto();
		loginRequestdto.setUserMail("chethana@gmail.com");
		loginRequestdto.setPassword("c");
		return loginRequestdto;
	}

	public User getUser() {
		user = new User();
		user.setUserMail("chethana@gmail.com");
		user.setPass("c");
		user.setRole(0);
		user.setPhone(123L);
		return user;
	}
	
	@Before
	public void setup() {
		loginResponsedto = getLoginResponse();
		loginRequestdto = getLoginRequest();
	}
	
	@Test
	public void testLogin() throws Exception {
		log.info("Entering into loginTest of LoginServiceImplementationTest");
		loginResponsedto = getLoginResponse();
		loginRequestdto = getLoginRequest();
		user = getUser();
		Mockito.when(loginRepository.findByUserMailAndPass(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(user));
		Optional<LoginResponsedto> response = loginServiceImplementation.login(loginRequestdto);
		Assert.assertNotNull(response);
	}
	
	@Test(expected=UserException.class)
	public void testLoginNegative() throws Exception {
		log.info("Entering into testLoginNegative of LoginServiceImplementationTest");
		Mockito.when(loginRepository.findByUserMailAndPass(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.ofNullable(null));
		loginServiceImplementation.login(loginRequestdto);
	}

}

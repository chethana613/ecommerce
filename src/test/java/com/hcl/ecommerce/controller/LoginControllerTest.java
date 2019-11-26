package com.hcl.ecommerce.controller;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.dto.LoginRequestdto;
import com.hcl.ecommerce.dto.LoginResponsedto;
import com.hcl.ecommerce.exception.UserException;
import com.hcl.ecommerce.service.LoginServiceImplementation;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginControllerTest {

	@Mock
	LoginServiceImplementation loginServiceImplementation;

	@InjectMocks
	LoginController loginController;
	
	LoginRequestdto loginRequestdto;
	LoginResponsedto loginResponsedto;

	public LoginRequestdto getUserLoginRequestdto() {
		loginRequestdto = new LoginRequestdto();
		loginRequestdto.setUserMail("a@gmail.com");
		loginRequestdto.setPassword("antey");
		return loginRequestdto;
	}

	public LoginResponsedto getUserLoginResponsedto() {
		loginResponsedto = new LoginResponsedto();
		loginResponsedto.setMessage("User Logged in Successfully");
		return loginResponsedto;
	}

	@Before
	public void setUp() {

		loginRequestdto = getUserLoginRequestdto();
		loginResponsedto = getUserLoginResponsedto();
	}

	@Test
	public void testLogin() throws Exception {
		Mockito.when(loginServiceImplementation.login(Mockito.any())).thenReturn(Optional.of(loginResponsedto));
		ResponseEntity<Optional<LoginResponsedto>> loginResponsedto = loginController.login(loginRequestdto);
		Assert.assertNotNull(loginResponsedto);

	}

	@Test(expected = UserException.class)
	public void testLoginNegative() throws Exception {
		Mockito.when(loginServiceImplementation.login(Mockito.any())).thenReturn(Optional.ofNullable(null));
		loginController.login(loginRequestdto);
	}
}

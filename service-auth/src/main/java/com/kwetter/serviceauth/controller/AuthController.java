package com.kwetter.serviceauth.controller;

import com.kwetter.serviceauth.repository.AuthDataConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthController
{
	@Autowired
	private AuthDataConnection authRepository;

	@PostMapping("/login")
	public int authenticateUser(@RequestHeader String username, @RequestHeader String password)
	{
		return authRepository.AuthUser(username, password);
	}
}

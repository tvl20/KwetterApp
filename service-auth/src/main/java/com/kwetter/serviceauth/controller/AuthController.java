package com.kwetter.serviceauth.controller;

import com.kwetter.serviceauth.dao.UserDao;
import com.kwetter.serviceauth.messaging.RabbitMessenger;
import com.kwetter.serviceauth.model.AuthoritiesEntity;
import com.kwetter.serviceauth.model.Authority;
import com.kwetter.serviceauth.model.TokenResponse;
import com.kwetter.serviceauth.model.UserEntity;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController
{
	@Value("${server.port}")
	private String localPortNumber;

	@Autowired
	private RabbitMessenger rabbitMessenger;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/register")
	@ResponseBody
	public String register(@RequestBody UserEntity registerUser)
	{
		if (userDao.findByUsername(registerUser.getUsername()) != null) return "{\"status\":\"Username already exists\"}";

		// Set default values for new user
		registerUser.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
		registerUser.setAccountNonExpired(true);
		registerUser.setCredentialsNonExpired(true);
		registerUser.setEnabled(true);

		HashSet<AuthoritiesEntity> set = new HashSet<>();
		set.add(new AuthoritiesEntity(registerUser, Authority.ROLE_USER));
		registerUser.setAuthorities(set);

		// Update other services that there is a new user
		rabbitMessenger.sendMessageNewUser(userDao.saveAndFlush(registerUser));

		System.out.println("Created User, Username: " + registerUser.getUsername() + ", pass (encoded): " + registerUser.getPassword());

		return "{\"status\":\"User created\"}";
	}

	@PostMapping("/login")
	@ResponseBody
	public TokenResponse login(@RequestBody UserEntity loginUser)
	{
		if (userDao.findByUsername(loginUser.getUsername()) == null) {
			System.out.println("No user found, returning null");
			return null;
		}

		System.out.println("User login, name: " + loginUser.getUsername() + ", pass: " + loginUser.getPassword());

		// Create Oauth login http request
		HttpHeaders headers = createAuthorizationHeader("client-secret");
		MultiValueMap<String, String> body = createLoginBody(loginUser.getUsername(), loginUser.getPassword());
		HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);

		System.out.println(request.toString());

		// Send request to own auth server
		RestTemplate template = new RestTemplate();
		String oauthUri = "http://localhost:8083/oauth/token";
//		String oauthUri = "http://localhost:" + Integer.parseInt(localPortNumber) + "/oauth/token";
		ResponseEntity<TokenResponse> response = template.postForEntity(oauthUri, request, TokenResponse.class);

		System.out.println(response.toString());

		return response.getBody();
	}

	private HttpHeaders createAuthorizationHeader(String clientSecret)
	{
		String auth = "clientId:" + clientSecret;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII) );

		return new HttpHeaders(){{
			set("Authorization", "Basic " + new String( encodedAuth ));
		}};
	}

	private MultiValueMap<String, String> createLoginBody(String username, String password)
	{
		MultiValueMap<String,String> bodyMap = new LinkedMultiValueMap<String,String>();
		bodyMap.add("username", username);
		bodyMap.add("password", password);
		bodyMap.add("grant_type","password");
		return bodyMap;
	}

}

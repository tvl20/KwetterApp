package com.kwetter.serviceauth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse
{
	@JsonProperty
	private String access_token;

	@JsonProperty
	public String username;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}

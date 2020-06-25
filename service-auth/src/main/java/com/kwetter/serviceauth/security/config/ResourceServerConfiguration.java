package com.kwetter.serviceauth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.io.IOException;

import static org.apache.commons.codec.Charsets.UTF_8;

@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperties.class)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String ROOT_PATTERN = "/**";

	private final SecurityProperties securityProperties;

	public ResourceServerConfiguration(final SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	@Autowired
	private TokenStore tokenStore;

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and().authorizeRequests().antMatchers("/oauth/token","/h2-console/**","/auth/register","/auth/login")
		    .permitAll().anyRequest().authenticated();
	}

	private String getPublicKeyAsString() {
		try {
			return new String(securityProperties.getJwt().getPublicKey().getInputStream().readAllBytes(), UTF_8);
		} catch (IOException e) {
			//log exception
			throw new RuntimeException(e);
		}

	}
}

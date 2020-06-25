package com.kwetter.serviceauth.security.service;

import com.kwetter.serviceauth.dao.UserDao;
import com.kwetter.serviceauth.model.AuthoritiesEntity;
import com.kwetter.serviceauth.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component("userDetailsService")
public class UserDetailsServiceImplementation implements UserDetailsService
{

	@Autowired
	private UserDao usersDao;

	@Override
	public UserDetails loadUserByUsername(String username)  {

		UserEntity usersEntity = usersDao.findByUsername(username);

		if (usersEntity == null) {
			throw new UsernameNotFoundException("user not found.");
		}

		Set<AuthoritiesEntity> roles = usersEntity.getAuthorities();

		Set<GrantedAuthority> authorities = new HashSet<>();

		for (AuthoritiesEntity role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority().toString()));
		}

		UserDetailsImplementation users = new UserDetailsImplementation();
		users.setUsername(usersEntity.getUsername());
		users.setPassword(usersEntity.getPassword());
		users.setAccountNonExpired(usersEntity.isAccountNonExpired());
		users.setCredentialsNonExpired(usersEntity.isCredentialsNonExpired());
		users.setAccountNonLocked(usersEntity.isAccountNonExpired());
		users.setEnabled(usersEntity.isEnabled());
		users.setAuthorities(authorities);
		return users;
	}
}
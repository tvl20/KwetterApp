package com.kwetter.serviceauth.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class UserEntity
{
	@Id
	@Column(columnDefinition = "VARCHAR(15)")
	private String username = "";

	private String password = "";

	@Column(name = "account_non_expired")
	private boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired;
	private boolean enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AuthoritiesEntity> authorities;

	// property accessors
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<AuthoritiesEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<AuthoritiesEntity> authorities) {
		this.authorities = authorities;
	}
}

package com.kwetter.serviceauth.model;

import javax.persistence.*;

@Entity
@Table(name ="authorities")
public class AuthoritiesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Enumerated(EnumType.STRING)
	private Authority authority;

	public AuthoritiesEntity(UserEntity user, Authority authority) {
		this.user = user;
		this.authority = authority;
	}

	public AuthoritiesEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
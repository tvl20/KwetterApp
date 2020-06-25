package com.kwetter.serviceauth.dao;

import com.kwetter.serviceauth.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long>
{

	UserEntity findByUsername(String username);

}

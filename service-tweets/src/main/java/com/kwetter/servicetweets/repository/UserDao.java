package com.kwetter.servicetweets.repository;

import com.kwetter.servicetweets.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long>
{
	UserEntity findByUsername(String username);
}

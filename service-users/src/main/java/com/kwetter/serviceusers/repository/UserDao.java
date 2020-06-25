package com.kwetter.serviceusers.repository;

import com.kwetter.serviceusers.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long>
{
	UserEntity findByUsername(String username);

	int countUsersByFollowingContains(UserEntity username);
}

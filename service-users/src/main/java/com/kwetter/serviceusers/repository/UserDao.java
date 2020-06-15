package com.kwetter.serviceusers.repository;

import com.kwetter.serviceusers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface UserDao extends JpaRepository<User, Long>
{
	User findByUsername(String username);

	int countUsersByFollowingContains(String username);
}

package com.kwetter.servicetweets.repository;

import com.kwetter.servicetweets.model.Tweet;
import com.kwetter.servicetweets.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetDao extends JpaRepository<Tweet, Long>
{
	List<Tweet> findByPoster(UserEntity poster);
}

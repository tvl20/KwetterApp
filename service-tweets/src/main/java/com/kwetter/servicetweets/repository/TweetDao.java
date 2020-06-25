package com.kwetter.servicetweets.repository;

import com.kwetter.servicetweets.model.Tweet;
import com.kwetter.servicetweets.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetDao extends JpaRepository<Tweet, Long>
{
	public final static String postsQuery = "SELECT * FROM tweet t WHERE t.username = :username ORDER BY t.post_date DESC";
	@Query(value = postsQuery, nativeQuery = true)
	List<Tweet> getPosts(String  username);

	public final static String timelineQuery = "SELECT * FROM tweet t WHERE t.username IN :usernames ORDER BY t.post_date DESC";
	@Query(value = timelineQuery, nativeQuery = true)
	List<Tweet> getTimeline(@Param("usernames") String[] usernames);
}

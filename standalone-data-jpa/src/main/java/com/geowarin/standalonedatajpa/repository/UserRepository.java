package com.geowarin.standalonedatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geowarin.standalonedatajpa.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {

	// Demonstrate query creation by method name
	// http://static.springsource.org/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	User findByName(String name);
	
	// Demonstrate the use of a simple JPQL query
	@Query("from User u where upper(u.name) = upper(:name)")
	User findByNameIgnoreCase(@Param("name") String name);
}

package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {
	
	
@Query("SELECT user FROM  UserDetails user WHERE user.email = :EmailSearch and user.password = :PasswordSearch")
Optional<UserDetails>  searchUserExist(@Param("EmailSearch")String EmailSearch,@Param("PasswordSearch")String PasswordSearch);

}

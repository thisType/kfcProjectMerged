package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<KFCMenu, Integer> {
      Optional<KFCMenu> findByPrice(Integer price);
	
}

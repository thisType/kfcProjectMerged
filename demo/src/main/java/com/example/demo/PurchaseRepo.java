package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<PurchaseEntity, Integer> {
	
	ArrayList<PurchaseEntity> findByUser(UserDetails user);

}

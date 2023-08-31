package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PurchaseEntity {
	
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Integer id;
	  
	  
	  @ManyToOne
	  private KFCMenu menu;
	  
	  @JsonIgnore
	  @ManyToOne
	  private UserDetails user;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public KFCMenu getMenu() {
		return menu;
	}


	public void setMenu(KFCMenu menu) {
		this.menu = menu;
	}


	public UserDetails getUser() {
		return user;
	}


	public void setUser(UserDetails user) {
		this.user = user;
	}
	  
	  

}

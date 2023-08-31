package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
	
	
	@Autowired
	MenuRepository repoMenu;
	@Autowired
	UserRepository repoUser;
	
	
	
	public KFCMenu getMenu(Integer price) {
		
		Optional<KFCMenu> option = repoMenu.findByPrice(price);
		if(option.isPresent()) {
			
			KFCMenu menu = option.get();
			System.out.println(" exist");
			System.out.println(menu.getDescription()+menu.getId());
			
		return  menu;
			
		} else {
			System.out.println("Opps");
			return null;
		}
		
		
		
		
		
	}
	public UserDetails createUser(UserDetails user) {
		
	  UserDetails newUser = repoUser.save(user);
	  return newUser;
		
		
		
	}
	
	public KFCMenu viewMenuById(Integer id) {
		
	Optional<KFCMenu> option = repoMenu.findById(id);
	
	return option.get();		
		
		
		
	}
	
	public Optional<UserDetails> checkUserAccount(String email, String password){
		
		
		Optional<UserDetails> option =repoUser.searchUserExist(email, password);
		return option;
		
	}
	
	
	
	

}

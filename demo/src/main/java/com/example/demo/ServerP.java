package com.example.demo;



import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class ServerP {
	
	@Autowired
	MenuService service;

	
	
	@Autowired
	PurchaseRepo purchaseRepo;
	
	@GetMapping("/menuById/{price}")
	public ResponseEntity<KFCMenu> searchPrice(@PathVariable("price") Integer price){
		      
	         KFCMenu menu = service.getMenu(price);
	         System.out.println("Called");
	         
	         if(menu != null) {
	        	HttpHeaders headers = new HttpHeaders();
	        	headers.setContentType(MediaType.APPLICATION_JSON);
	        	 
	        	 
	        	return new ResponseEntity<KFCMenu>(menu,headers,HttpStatus.OK);
	        	 
	        	 
	         } else {
	        	 
	        	 
	        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	         }
	
		
		}
	
	@GetMapping("/checkUserLogin")
	public ResponseEntity<String> loginSession(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userLogin")== null) {
			
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			
			
			
			return new ResponseEntity<String>("Logged in",HttpStatus.OK);
		}
		
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/addToCart")
	public ResponseEntity<String> addToCart(HttpServletRequest request){
		
		String id = request.getParameter("id");
		String quantity = request.getParameter("quantity");
		HttpSession session = request.getSession();
		int quantityInt = Integer.parseInt(quantity);
		ArrayList<String> cartList;
		if(session.getAttribute("cart") != null) {
			
			cartList = (ArrayList<String>) session.getAttribute("cart");
			for(int x=1; x<=quantityInt;x++) {
				
				
				
				cartList.add(id);
			}
			
			session.setAttribute("cart", cartList);
			
			
			
			
		} else {
			
			
			cartList = new ArrayList<String>();
          for(int x=1; x<=quantityInt;x++) {
				
				
				
				cartList.add(id);
			}
			
			session.setAttribute("cart", cartList);
	
			
			
		}
		
		
		  System.out.println("Adding...."+cartList);
		
		return new ResponseEntity<String>("added",HttpStatus.OK);
		
		
		
		
		}
	
	
	@GetMapping("/addToCart")
	public ResponseEntity<ArrayList<KFCMenu>> listCart(HttpServletRequest req){
		
		HttpSession session = req.getSession();
		ArrayList<KFCMenu> menu = new ArrayList<KFCMenu>();
		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String>) session.getAttribute("cart");
		System.out.println("cart"+ list);
		if(list != null) {
			for(String xString: list) {
				
				int x = Integer.parseInt(xString);
				menu.add(service.viewMenuById(x));
				
				
			
				
				
			}
			return new ResponseEntity<ArrayList<KFCMenu>>(menu,HttpStatus.OK);
			
			
			
			
			
			
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		
	}
	@DeleteMapping("/deleteCart")
	public ResponseEntity<String> listCartDelete(HttpServletRequest req){
		
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		System.out.println("delete called");
		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String>) session.getAttribute("cart");
		
		list.remove(id);
		session.setAttribute("cart", list);
		
		return  new ResponseEntity<String>("deleted",HttpStatus.OK);
		
		
	}
	@PostMapping("/checkUserExist")
	public ResponseEntity<String> checkUser(HttpServletRequest req){
		   System.out.println("Check user called");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Optional<UserDetails> option =  service.checkUserAccount(email, password);
		
		if(option.isPresent()) {
			
		HttpSession  session = req.getSession();
		session.setAttribute("userLogin", option.get());
			return  new ResponseEntity<String>("logged in",HttpStatus.OK);
			
		} else {
			
			
			return new ResponseEntity<String>("sorry",HttpStatus.NOT_FOUND);
		}
		
		
	}
	@PostMapping("/createNewUser")
	public ResponseEntity<String> createUser(HttpServletRequest req){
		
		String firstname = req.getParameter("firstName");
		String secondname = req.getParameter("secondName");
		String email = req.getParameter("email");
		String telephone = req.getParameter("telephone");
		String password = req.getParameter("password");
		
		UserDetails user = new UserDetails();
		user.setFirstName(firstname);
		user.setLastName(secondname);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setPassword(password);
		
		UserDetails  user1 =service.repoUser.save(user);
		
		HttpSession session = req.getSession();
		session.setAttribute("userLogin", user1);
		
		
		
		return new ResponseEntity<String>("Okay",HttpStatus.OK);
		
		
		}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/purchaseItems")
	public ResponseEntity<String>  purchaseItems(HttpServletRequest req){
		System.out.println("Purchase called");
		HttpSession session = req.getSession();
		
		ArrayList<String> cartList = (ArrayList<String>) session.getAttribute("cart");
		UserDetails user = (UserDetails) session.getAttribute("userLogin");
		
		if(cartList != null & user !=null) {
			
			for( String id : cartList) {
				
			PurchaseEntity  purchaseEntity = new PurchaseEntity();
			
			KFCMenu kfcMenu =  service.viewMenuById(Integer.parseInt(id));
			
			purchaseEntity.setMenu(kfcMenu);
			purchaseEntity.setUser(user);
			 purchaseRepo.save(purchaseEntity);
			
			
				
				
				
				
				
				
				
				
			}
			
			
			
			return new ResponseEntity<String>("purchase successful",HttpStatus.OK);
			
			
			
		} else {
			
			return new ResponseEntity<String>("Sorry",HttpStatus.NOT_FOUND);
		}
		
		
		
		
	}
	
	@GetMapping("/purchaseHistory")
	public ResponseEntity<ArrayList<PurchaseEntity>> purchaseHistory(HttpServletRequest req){
		HttpSession session = req.getSession();
		
	   UserDetails userDetails = (UserDetails) session.getAttribute("userLogin");
	   
	   ArrayList<PurchaseEntity>  history = purchaseRepo.findByUser(userDetails);
		
	   
	   if(userDetails != null & history !=null) {
		   
		   
		   return new ResponseEntity<ArrayList<PurchaseEntity>>(history,HttpStatus.OK);
		   
		   
	   } else {
		   
		   
		   
		   return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	   }
		
		
		
		
	}
	@GetMapping("/userDetails")
	public ResponseEntity<UserDetails> getUser(HttpServletRequest req){
		  HttpSession session = req.getSession();
		 UserDetails userDetails = (UserDetails) session.getAttribute("userLogin");
		  
		if(userDetails != null) {
			
			return new ResponseEntity<UserDetails>(userDetails,HttpStatus.OK);
			
			
			
		} else {
			
			
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@GetMapping("/logoutUser")
	public ResponseEntity<String> logoutUser(HttpServletRequest req){
		    
		
		 HttpSession session = req.getSession();
		 session.removeAttribute("userLogin");
		 
		 return new ResponseEntity<>("OK",HttpStatus.OK);
		
		
	}
	
	
	

}

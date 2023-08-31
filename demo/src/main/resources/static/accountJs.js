let loginForm  = document.querySelector("#loginForm");
let  message = document.querySelector("p.message");
let  signinForm = document.querySelector("#signinForm");
let  account = document.querySelector("#account");
 console.log("heeey im in");

function doAfterThisSecond(todo, seconds){
	
	
	
	setTimeout(todo,seconds*1000);
}

function setMessage(messageText, error = false){
	  if(!error){
	 message.textContent = messageText;
	 } else {
		 
		 
		message.textContent = messageText;
	 }
	
	
}

async function loginUser(){
	
	let urlBody = new URLSearchParams();
	
	urlBody.append("email",loginForm.elements.email.value);
	urlBody.append("password",loginForm.elements.password.value);
	
	let loginResponse = await fetch("/checkUserExist",{
      method:"POST",credentials:"same-origin", body:urlBody
    });
    
    
    if(loginResponse.ok){
		
		account.style.display ="inline";
	   setMessage("login successful.  We are redirecting you to your to resume your session in 5seconds or you can visit your account at top of the page. ");
	doAfterThisSecond(()=>{   window.location.href= "Welcome.html"; },5);
		
		
	} else{
	setMessage("Failed to login", true);
	
	}
	
	}
	async function createUser(){
		
		
		let urlBody = new URLSearchParams();
		urlBody.append("firstName",signinForm.elements.firstName.value);
		urlBody.append("secondName",signinForm.elements.secondName.value);
		urlBody.append("email",signinForm.elements.email.value);
		urlBody.append("telephone",signinForm.elements.telephone.value);
		urlBody.append("password",signinForm.elements.password.value);
		console.log(urlBody.toString());
		let signinResponse = await fetch("/createNewUser",{
      method:"POST",credentials:"same-origin", body:urlBody
    });
    
          if(signinResponse.ok){
			  
			  account.style.display ="inline";
	   setMessage("Signin successful.  We are redirecting you to your to resume your session in 5seconds or you can visit your account at top of the page. ");
	doAfterThisSecond(()=>{   window.location.href= "Welcome.html"; },5);
			  
			  
			  
		  } else{
	setMessage("Creation of account failed to login. ", true);
	
	}
	
    
		
	}
	
signinForm.addEventListener("submit",(e)=>{
	e.preventDefault();
	createUser();
	
	
})	;
loginForm.addEventListener("submit",(e)=>{
	e.preventDefault();
	
	loginUser();

});
	
	
	
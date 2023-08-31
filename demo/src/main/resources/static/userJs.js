let name  = document.querySelector("#name");
let email = document.querySelector("#email");
let tel =document.querySelector("#tel");


function usertext(text){

	
  
  name.textContent = text.firstName+" " +text.lastName;
  email.textContent =text.email;
  tel.textContent = text.telephone;

}


async function fetchServlet(v,todo){



 	try{

 		let response = await fetch(v, {
 			method:"GET",credentials:"same-origin"
 		});
          
          if(response.ok){

          let result  = await response.json();
          console.log(result);
           todo(result);
         
 }



 	} catch(error){

        console.log('problem');
        console.log(error)

 	}
 }
 fetchServlet("/userDetails",usertext);

let ul = document.querySelector("ul");
 function historyPop(text){

 	

   for(let x of text){

      let li  = document.createElement("li");
      li.textContent = x.id + " "+ x.menu.kfcName+ " "+ x.menu.price;
      ul.appendChild(li);

   }


}
fetchServlet("/purchaseHistory",historyPop);


async function logout(){



 	try{

 		let response = await fetch('/logoutUser', {
 			method:"GET",credentials:"same-origin"
 		});
          
          if(response.ok){

          window.location.href= "Welcome.html"; 
         
 }



 	} catch(error){

        console.log('problem');
        console.log(error)

 	}
 }
 let btnLogout = document.querySelector("button.logout");

 btnLogout.addEventListener("click",(e)=>{      logout();});



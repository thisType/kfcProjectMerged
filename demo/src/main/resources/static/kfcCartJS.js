

let main = document.querySelector("main");
let  pltics = document.querySelector("p.analysis");
let stats =  0;
let loggedIn = false;

let  multic  = document.querySelector("#login");

 function statsTrack(v,x=0){

  if(x==0){
   
   stats += Number(v);
    pltics.textContent = stats +"Ksh";


  }  else {

  	stats -= Number(v);
  	pltics.textContent = stats +"Ksh";
  }


 }



function removeList(elm){
   
    statsTrack(elm.parentElement.dataset["price"],1);
   let parent  = elm.parentElement;
   main.removeChild(parent);


}




async function  sendDelete(b){

     let url = new URLSearchParams();
     url.append("id",b.dataset["id"]);

 	try{

 		let response = await fetch("/deleteCart?"+url.toString(), {
 			method:"DELETE",credentials:"same-origin" 
 		});



 		if(response.ok){

 		removeList(b);
 		}




 	} catch(error){

        console.log('problem');
        console.log(error)

 	}
 }


function cartList(text){
 
//let  list = text.split("\n");
console.log(text);

for(let x of text){


let p = document.createElement("p");
p.dataset["price"] = x.price;
p.textContent = x.kfcName+"->"+ x.description +" "+ x.price+"ksh";
let btn  = document.createElement("button");
btn.dataset['id'] = x.id;
btn.className ="remove";
btn.textContent ="remove";
btn.addEventListener('click',(e)=>{
      sendDelete(e.target);
     });
p.appendChild(btn);
main.appendChild(p);

  statsTrack(x.price);
}


}


async function getCart(){


 	try{

 		let response = await fetch('/addToCart', {
 			method:"GET",credentials:"same-origin"
 		});
       if(response.ok){

          let result  = await response.json();
          console.log(result);
         cartList(result);

    }


 	} catch(error){

        console.log('problem');
        console.log(error)

 	}
 }
 getCart();
 let purchase = document.querySelector("button.purchase");


 async function  purchaseItems(){



 	try{

 		let response = await fetch('/purchaseItems', {
 			method:"POST",credentials:"same-origin" 
 		});



 		if(response.ok){

 			alert("purchase successful. Check your email");
 		} else {
 			alert("login  first to purchase. Or no cart");

 		}




 	} catch(error){

        console.log('problem');
        console.log(error)

 	}
 }

purchase.addEventListener('click',()=>{

  if(login == false){


  	alert("Create an  account or login");

  } else {

  	purchaseItems();
  }


});


async function checkLogin(){
  let url = new URLSearchParams();
  url.append("userId","userId");
  
  try{
    
    let response = await fetch("/checkUserLogin",{
      method:"GET",credentials:"same-origin"
    });
    
    if(response.ok){
           loggedIn = true;
      
            multic.textContent="Account";
      
    }
    
    
  } catch(error) {
    console.log("session error");
    
    }
  
}
checkLogin();

multic.addEventListener("click",(e)=>{

if(loggedIn){

    window.location.href= "User.html"; 

} else{
 window.location.href= "Account.html"; 

}
});


let login  =  document.querySelector("#login");
let account  = document.querySelector("#cart");


let nameKfc  = document.querySelector("#name");
let des  = document.querySelector("#description");
let price = document.querySelector("#price");
let img = document.querySelector("#show");
let id  =0;
let loggedIn = false;

let  multic  = document.querySelector("#login");
let  cart  = document.querySelector("#cart");

function populate(list){
  
   
  id = list.id;
  nameKfc.textContent = list.kfcName;
  des.textContent = list.description;
  price.textContent = "Ksh"+ list.price;
  img.src = list.imageName;


}


async function getData(price){



 	try{

 		let response = await fetch("/menuById/"+price, {
 			method:"GET",credentials:"same-origin"
 		});


          let result  = await response.json();
          console.log("Result "+ result);
         populate(result);




 	} catch(error){

        console.log('problem');
        console.log(error);

 	}
 }


getData(String(300));


let priceButtons = document.querySelectorAll(".category button");

for(let btn of priceButtons){


	btn.addEventListener("click",(e)=>{     getData(e.target.textContent);});
}



let numOrder  = document.querySelector("#quantity");
let add = document.querySelector("#add");



async function  addCart(b,c){

let url = new URLSearchParams();
url.append('quantity',b);
url.append('id',c)

 	try{

 		let response = await fetch('/addToCart', {
 			method:"POST",credentials:"same-origin" , body:url
 		});



 		if(response.ok){

 			alert("added "+b+" items. ");
 		}




 	} catch(error){

        console.log('problem');
        console.log(error)

 	}
 }

add.addEventListener('click',()=>{    addCart(numOrder.value,id);});

async function checkLogin(){
  
  
  try{
    
    let response = await fetch("/checkUserLogin",{
      method:"GET",credentials:"same-origin"
    });
    
    if(response.ok){
           loggedIn = true;
      
            multic.textContent="Account";
      
    } else {
      console.log("Not okay");
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
cart.addEventListener("click",(e)=>{

window.location.href= "kfcCart.html"; 


});






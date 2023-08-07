let navbar = document.querySelector('.navbar');

document.querySelector('#menu-icon').onclick = () =>{
  navbar.classList.toggle('active');
  cartItem.classList.remove('active');
  searchForm.classList.remove('active');

}

// add to cart js from here..............

let cartItems = [];

let cartItem = document.querySelector('.cart-container');

document.querySelector('#shopping-cart-icon').onclick = () =>{
  cartItem.classList.toggle('active');
  navbar.classList.remove('active');
  searchForm.classList.remove('active');
}

if(document.readyState == "loading") {
  document.addEventListener('DOMContentLoaded', ready)
} else{
  ready();
}

// Making function
function ready() {
  // Remover Items From Cart
  var removeCartButton = document.getElementsByClassName('fa-times');
  console.log(removeCartButton)

  for(var i=0; i<removeCartButton.length; i++) {
    var button = removeCartButton[i];
    button.addEventListener('click', removeCartItem);
  }

  // Quanity Chages
  var qunatityInputs = document.getElementsByClassName('items-quantity');
  for(var i=0; i<qunatityInputs.length; i++) {
    var input = qunatityInputs[i];
    input.addEventListener("change", quantityChanged);
  }

  // Add to Cart
  var addCart = document.getElementsByClassName('add-cart');
  for(var i=0; i<addCart.length; i++) {
    var cartButtons = addCart[i];
    cartButtons.addEventListener('click', addCartClicked);
  }
}



// Remove Items form Cart
function removeCartItem(event) {
  var buttonClicked = event.target;
  buttonClicked.parentElement.remove();
  updatetoatl();
}

// Quantity Changed
function quantityChanged(event) {
  var  input = event.target;
  if(isNaN(input.value) || input.value <= 0) {
    input.value = 1;
  }
  updatetoatl();
}

// Add to Cart
function addCartClicked(event) {
  var cartButtons = event.target;
  var shopProducts =  cartButtons.parentElement.parentElement;
  var title = shopProducts.getElementsByClassName('product-name')[0].innerText;
  var price = shopProducts.getElementsByClassName('product-price')[0].innerText;
  var productImg = shopProducts.getElementsByClassName('Product-Image')[0].src;
  addProductToCart(title, price,productImg);
  // updatetoatl();
  console.log(title,price, productImg);
}



function addProductToCart(title, price, productImg) {
  var cartShopBox = document.createElement('div');
  cartShopBox.classList.add('items');
  var cartItems = document.getElementsByClassName('cart-container')[0];
  var cartItemNames = cartItems.getElementsByClassName('cart-product-title');
  for(var i=0; i<cartItemNames.length; i++) {
    if(cartItemNames[i].innerText == title) {
      alert('you have already add this item to cart');
      return;
    }
  }

var cartBoxContent =  `
                      <span class="fas fa-times"></span>
                      <img src="${productImg}" alt="">
                      <div class="content">
                      <div class="cart-product-title">
                      <h3>${title}</h3>
                      </div>
                      <div class="price">${price}</div>
                      <input type="number" value="1" class="items-quantity">
                      </div>`;

cartShopBox.innerHTML = cartBoxContent
cartItems.append(cartShopBox)
cartShopBox.getElementsByClassName('fa-times')[0].addEventListener('click', removeCartItem)
cartShopBox.getElementsByClassName('items-quantity')[0].addEventListener('change', quantityChanged)

}

// update Totals
function updatetoatl() {
  var cartContenet  = document.getElementsByClassName('cart-container')[0];
  var cartBoxes = cartContenet.getElementsByClassName('items');
  var total =  0;
  for(var i=0; i<cartBoxes.length; i++) {
    var cartBox = cartBoxes[i];
    var priceElement = cartBox.getElementsByClassName('price')[0];
    var price = parseFloat(priceElement.innerText.replace("Rs.", ""));
    var quantityElement = cartBox.getElementsByClassName('items-quantity')[0];
    var quantity = quantityElement.value;
    total = total + (price * quantity) ;
    // if price contain same rupees value
    total = Math.round(total * 100)/100;

    document.getElementsByClassName('total-price')[0].innerText = 'Rs.' + total;
  }
}

// to here.........................



let searchForm = document.querySelector('.search-form');

document.querySelector('#search-icon').onclick = () =>{
  searchForm.classList.toggle('active');
  navbar.classList.remove('active');
  cartItem.classList.remove('active');
}

window.onscroll = () => {
  navbar.classList.remove('active');
  cartItem.classList.remove('active');
  searchForm.classList.remove('active');
}


// window.addEventListener("load", function() {
//   setTimeout(
//     function open(event) {
//       document.querySelector(".container").style.display = "block";
//     },
//     5000
//   )
// });


// 31/01/2023

// document.querySelector("#close").addEventListener
// ("click", function(){
//   document.querySelector(".container").style.display = "none";
//
// });
//
// document.querySelector('.navbar-login').addEventListener
// ("click", function() {
//   document.querySelector('.container').style.display = "block";
// })


const SignUp = document.querySelector(".SignUp");
const Login = document.querySelector(".Login");
const move = document.querySelector(".move");
const signupjs = document.querySelector(".signup-js");
const loginjs = document.querySelector(".signin-js");


Login.addEventListener("click", ()=> {
  move.classList.add("rightbtn");
  loginjs.classList.add("login-form");
  signupjs.classList.remove("signup-form");
  move.innerHTML= "SignIn";
});

SignUp.addEventListener("click", ()=> {
  move.classList.remove("rightbtn");
  loginjs.classList.remove("login-form");
  signupjs.classList.add("signup-form");
  move.innerHTML= "Signup";
});



const btns = document.querySelectorAll(".nav-btn");
    const slides = document.querySelectorAll(".video-slide");
    const contents = document.querySelectorAll(".content-abt");


    var sliderNav = function(manual) {
      btns.forEach((btn) => {
        btn.classList.remove("active");
      });

      slides.forEach((slide) => {
        slide.classList.remove("active");
      });

      contents.forEach((content) => {
        content.classList.remove("active");
      });

      btns[manual].classList.add("active");
      slides[manual].classList.add("active");
      contents[manual].classList.add("active");

    }

    btns.forEach((btn, i) => {
      btn.addEventListener('click', () => {
        sliderNav(i);
      });
    });











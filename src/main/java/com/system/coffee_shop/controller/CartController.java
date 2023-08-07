package com.system.coffee_shop.controller;

import com.system.coffee_shop.entity.Cart;
import com.system.coffee_shop.entity.Product;
import com.system.coffee_shop.pojo.CartPojo;
import com.system.coffee_shop.pojo.ProductPojo;
import com.system.coffee_shop.services.CartService;
import com.system.coffee_shop.services.ProductService;
import com.system.coffee_shop.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/addCart")
    public String displayCart(Principal principal, Model model , CartPojo cartPojo){
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<Cart> list = cartService.fetchAll(id);


        model.addAttribute("cart",  cartPojo);
        model.addAttribute("cartItems", list);

        return "/cart";
    }

    @GetMapping("/add/{id}")
    public String saveToCart(@PathVariable Integer id, Principal principal){
        cartService.saveToCart(id, principal);
        return "redirect:/shop";
    }

    @PostMapping("/updateQuantity/{id}")
    public String updateQuantity(@Valid CartPojo cartPojo){
        Cart cart = cartService.fetchOne(cartPojo.getId());
        cartService.updateQuantity(cart);
        return "redirect:/cart";
    }

//    @PostMapping("/updateQuantity/{id}")
//    public String updateQuantity(@Valid ProductPojo productPojo){
//        Cart cart = cartService.fetchOne(productPojo.getId());
////        cart.setQuantity(productPojo.getQuantity());
//        productService.updateQuantity(cart);
//        return "redirect:/cart";
//    }

    @GetMapping("/remove/{id}")
    public String deleteCartItem(@PathVariable("id") Integer id){
        cartService.deleteFromCart(id);
        return "redirect:/cart/addCart";
    }

//    @PostMapping("/addToCart")
//    public String saveToCart(@Valid CartPojo cartPojo){
//        cartService.saveToCart(cartPojo);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/updateQuantity")
//    public String updateQuantity(@Valid CartPojo cartPojo, Principal principal){
//        Integer userId = userService.findByEmail(principal.getName()).getId();
//        List<Cart> cart = cartService.fetchAll(userId);
//
//        for (Cart i:cart){
//            cartService.updateQuantity(cartPojo.getQuantity(), userId, cartPojo.getId());
//        }
//
//        return "redirect:/cart";
//    }
//
//    @DeleteMapping("/deleteCart/{id}")
//    public String deleteWishlistItem(@PathVariable("id") Integer id, Principal principal){
//        User user = userService.findByEmail(principal.getName());
//        cartService.deleteFromCart(user.getId(), id);
//        return "redirect:/cart";
//    }

    @GetMapping("/checkout")
    public String getCheckout() {
        return "checkout";
    }
}



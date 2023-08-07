package com.system.coffee_shop.services;

import com.system.coffee_shop.pojo.CartPojo;
import com.system.coffee_shop.entity.Cart;

import java.security.Principal;
import java.util.List;

public interface CartService {
    String saveToCart(Integer id, Principal principal);

//    CartPojo saveToCart(CartPojo cartPojo);

    String deleteFromCart(Integer id);

    String updateQuantity(Cart cart);

    List<Cart> fetchAll(Integer id);

    List<Cart> fetchAvailable(Integer id);

    Cart fetchOne(Integer id);

    String checkout(Integer id, CartPojo pojo, List<Cart> itemsToPurchase);

    String updateProduct(double quantity, Integer id);
}

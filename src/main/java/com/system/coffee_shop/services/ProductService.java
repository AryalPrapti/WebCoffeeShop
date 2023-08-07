package com.system.coffee_shop.services;

import com.system.coffee_shop.entity.Cart;
import com.system.coffee_shop.entity.Product;
import com.system.coffee_shop.pojo.ProductPojo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductPojo saveProduct(ProductPojo productPojo) throws IOException;
    List<Product> fetchAll();
    Product getSingle(Integer id);

//    List<Product> fetchByCategory(Integer id);

    List<Product> fetchNew();
    List<Product> trending();
    List<Product> mostPopular();
    List<Product> bestSeller();
    Map<Integer, Double> comparePrice(List<Product> products);

    void deleteById(Integer id);

//    void updateQuantity(Cart cart);
//
//    void deleteFromCart(Integer id);
}



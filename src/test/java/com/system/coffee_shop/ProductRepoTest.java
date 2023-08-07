package com.system.coffee_shop;

import com.system.coffee_shop.entity.Product;
import com.system.coffee_shop.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;

    @Test
    @Order(1)
    public void saveProductTest() {

        Product product = Product.builder()
                .product_name("late")
                .product_price(250)
                .build();
        productRepo.save(product);
        Assertions.assertThat(product.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getProductTest() {

        Product product = Product.builder()
                .product_name("late")
                .product_price(250)
                .build();
        productRepo.save(product);
        Product productCreated = productRepo.findById(product.getId()).get();
        Assertions.assertThat(productCreated.getId()).isEqualTo(product.getId());

    }

}

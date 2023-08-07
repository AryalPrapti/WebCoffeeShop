package com.system.coffee_shop;

import com.system.coffee_shop.entity.Cart;
import com.system.coffee_shop.repo.CartRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartTest {

    @Autowired
    private CartRepo cartRepo;
    @Test
    @Order(1)
    public void saveCartTest() {

        Cart cart= Cart.builder()
                .quantity(12)
                .status("coffee")
                .build();
        cartRepo.save(cart);
        Assertions.assertThat(cart.getId()).isGreaterThan(0);
    }
}

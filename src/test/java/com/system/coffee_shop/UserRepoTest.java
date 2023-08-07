package com.system.coffee_shop;

import com.system.coffee_shop.entity.User;
import com.system.coffee_shop.repo.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    @Order(1)
    public void saveUserTest() {
        User user = User.builder()
                .fullName("bishal Shrestha")
                .email("bishal@gmail.com")
                .phone("9800121245")
                .password("12334")
                .build();
        userRepo.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void getUserTest() {
        User user = User.builder()
                .email("a@g.com")
                .password("1234")
                .build();
        userRepo.save(user);
        User userCreated = userRepo.findById(user.getId()).get();
        Assertions.assertThat(user.getId()).isEqualTo(userCreated.getId());
    }

    @Test
    @Order(3)
    public void getListOfUserTest(){
        User user = User.builder()
                .fullName("khilraj")
                .email("bishal11@g.com")
                .phone("9882565456")
                .password("123456")
                .build();

        userRepo.save(user);
        List<User> User = userRepo.findAll();
        Assertions.assertThat(User.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateUserTest(){

        User user = User.builder()
                .fullName("Bishal Shrestha")
                .email("bishal123@g.com")
                .phone("98888888")
                .password("1234")
                .build();


        userRepo.save(user);

        User user1  = userRepo.findById(user.getId()).get();

        user1.setFullName("Khilraj Shrestha");

        User userUpdated  = userRepo.save(user);

        Assertions.assertThat(userUpdated.getFullName()).isEqualTo("Khilraj Shrestha");

    }

    @Test
    @Order(5)
    public void deleteUserTest(){

        User user = User.builder()
                .fullName("Bishal Shrestha")
                .email("bishal111@g.com")
                .phone("9823254568")
                .password("123456")
                .build();


        userRepo.save(user);
        User user1 = userRepo.findById(user.getId()).get();
        userRepo.delete(user1);

        User user2 = null;
        Optional<User> optionalUser = userRepo.findByEmail("bishal@gmail.com");
        if(optionalUser.isPresent()){
            user2 = optionalUser.get();
        }

        Assertions.assertThat(user2).isNull();
    }
}

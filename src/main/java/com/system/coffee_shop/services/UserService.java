package com.system.coffee_shop.services;

import com.system.coffee_shop.entity.User;
import com.system.coffee_shop.pojo.UserPojo;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void saveUser(UserPojo userPojo);
    User findByEmail(String email);
    User fetchById(Integer id);
    UserPojo updateUser(UserPojo signUpPojo)throws IOException;
    List<User> fetchAll();
    // 14/02/2023
    User findByUsername(String fullName);

    String updateResetPassword(String email);

    void processPasswordResetRequest(String email);

    void sendEmail();
    // ........................

    // 15/02/2023
//    UserPojo getCurrentUser();
//    void saveUser(User user);
}

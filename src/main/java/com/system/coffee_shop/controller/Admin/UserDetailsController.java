package com.system.coffee_shop.controller.Admin;

import com.system.coffee_shop.entity.User;
import com.system.coffee_shop.pojo.UserPojo;
import com.system.coffee_shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserDetailsController {

    private final UserService userService;


    @GetMapping("/userDetails")
    public String getUserDetails(Model model) {
        List<User> users = userService.fetchAll();

        model.addAttribute("userList" ,users.stream().map(user ->
                User.builder()
                        .id(user.getId())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .build()
        ));
        return "Admin/user-list";
    }
}



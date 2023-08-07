package com.system.coffee_shop.controller;

import com.system.coffee_shop.entity.User;
import com.system.coffee_shop.pojo.ProductPojo;
import com.system.coffee_shop.pojo.UserPojo;
import com.system.coffee_shop.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String getPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("user", new UserPojo());
            return "user";
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/logout")
    public String logout(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";}

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo){
        userService.saveUser(userPojo);
        return "redirect:/login";
    }

    @GetMapping("/list/{id}")
    public String getUserDetails(@PathVariable("id") Integer id, Model model) {
        User user =userService.fetchById(id);
        model.addAttribute("update", new UserPojo(user));
        model.addAttribute("userdata",userService.fetchById(id));
        return "profile";
    }
    @PostMapping("/update")
    public String updateUser(@Valid UserPojo signUpPojo)throws IOException {
        userService.updateUser(signUpPojo);
        return "redirect:/homepage";
    }

    @GetMapping("/create")
    public String getCreate() {
        return "user";
    }

    @GetMapping("/forgot")
    public String getForgot() {
        return "/forgot_pass";
    }

    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model){
        model.addAttribute("users",new UserPojo());
        return ("forgot_pass");
    }

    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("email") String email, Model model, @Valid UserPojo userPojo){
        userService.processPasswordResetRequest(userPojo.getEmail());
        model.addAttribute("message","Your new password has been sent to your email Please " +
                "check your inbox");
        return "redirect:/login";
    }
}

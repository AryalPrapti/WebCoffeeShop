package com.system.coffee_shop.controller;

import com.system.coffee_shop.entity.Cart;
import com.system.coffee_shop.entity.Category;
import com.system.coffee_shop.entity.Product;
import com.system.coffee_shop.entity.User;
import com.system.coffee_shop.pojo.ProductPojo;
import com.system.coffee_shop.pojo.UserPojo;
import com.system.coffee_shop.repo.UserRepo;
import com.system.coffee_shop.services.CartService;
import com.system.coffee_shop.services.ProductService;
import com.system.coffee_shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.security.PrivateKey;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;


    @GetMapping("/dashboard")
    public String getHomePage(Model model, Principal principal, Authentication authentication) {
        if (principal==null){
            return "redirect:/login";
        }
//            String fullName = principal.getName();
            model.addAttribute("users", userService.findByEmail(principal.getName()));



        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/admin/landing";
                }
            }
        }

//        if (principal != null) {
//            String username = principal.getName();m
//            User user = userRepo.findByUsername(username);
//            model.addAttribute("user", user);
//        }


        List<Product> products = productService.fetchAll();
        model.addAttribute("product", products);

        Integer id = userService.findByEmail(principal.getName()).getId();
        List<Cart> list = cartService.fetchAll(id);

        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }
        List<Product> newProducts = productService.fetchNew();
        model.addAttribute("newProducts", newProducts);

        List<Product> trendingProducts = productService.trending();
        model.addAttribute("trending", trendingProducts);

        model.addAttribute("total", total);
        model.addAttribute("cartItems", list);

        return "home";
    }

    @GetMapping("viewCart")
    public String viewCart(Principal principal, Model model){
        Integer id = userService.findByEmail(principal.getName()).getId();

        List<Cart> list = cartService.fetchAll(id);

        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }

        model.addAttribute("total", total);
        model.addAttribute("cartItems", list);

        return "cart";
    }


    @GetMapping("/add")
    public String createUser(Model model){
        model.addAttribute("product",new ProductPojo());
        return "add_product";
    }

    @GetMapping("/contact")
    public String contactPage(Principal principal, Model model){

        Integer id = userService.findByEmail(principal.getName()).getId();

        List<Cart> list = cartService.fetchAll(id);

        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }

        model.addAttribute("total", total);
        model.addAttribute("cartItems", list);

        return "/contact";
    }

    @GetMapping("/about")
    public String aboutPage(Principal principal, Model model){
        Integer id = userService.findByEmail(principal.getName()).getId();

        List<Cart> list = cartService.fetchAll(id);
        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }

        model.addAttribute("total", total);
        model.addAttribute("cartItems", list);

        return "/about";
    }

    @GetMapping("/blogs")
    public String blogPage(Principal principal, Model model){

        Integer id = userService.findByEmail(principal.getName()).getId();

        List<Cart> list = cartService.fetchAll(id);
        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }

        model.addAttribute("total", total);
        model.addAttribute("cartItems", list);

        return "/blog";
    }

    @GetMapping("/shop")
    public String productsPage(Principal principal, Model model){

        Integer id = userService.findByEmail(principal.getName()).getId();

        List<Cart> list = cartService.fetchAll(id);
        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }

        model.addAttribute("total", total);
        model.addAttribute("cartItems", list);

        List<Product> products = productService.fetchAll();
        model.addAttribute("products", products);


        return "/shop";
    }

    @GetMapping("/product/{productId}")
    public String productDetailsPage(@PathVariable Integer productId, Principal principal, Model model){

        Integer id = userService.findByEmail(principal.getName()).getId();

        List<Cart> list = cartService.fetchAll(id);
        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }

        model.addAttribute("id", id);

        model.addAttribute("total", total);
        model.addAttribute("cartItems", list);

//        Product product = productService.getSingle(productId);
//        model.addAttribute("product",product);


//        List<Product> related = productService.fetchByCategory(product.getProduct_category().getId());
//        Map<Integer, Double> discount = productService.comparePrice(related);
//
//        List<Product> getFour = new ArrayList<>(4);
//        for (int i=0; i<related.size()&&i<4; i++){
//            if (!Objects.equals(related.get(i).getId(), product.getId())){
//                getFour.add(related.get(i));
//            }
//        }
//
//        model.addAttribute("related", getFour);

//        List<Category> categories = categoryService.fetchAll();
//        model.addAttribute("categories", categories);

        List<Product> trendingProducts = productService.trending();
        model.addAttribute("trending", trendingProducts);

//        Map<Integer, Double> popularDiscount = productService.comparePrice(trendingProducts);
//        model.addAttribute("popularDiscount", popularDiscount);
//
//        List<Product> mostPopular = productService.mostPopular();
//        model.addAttribute("popular", mostPopular);

        return "/product-details";
    }
}

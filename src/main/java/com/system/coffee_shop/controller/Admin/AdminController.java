package com.system.coffee_shop.controller.Admin;

import com.system.coffee_shop.entity.Product;
import com.system.coffee_shop.entity.User;
import com.system.coffee_shop.pojo.ProductPojo;
import com.system.coffee_shop.services.ProductService;
import com.system.coffee_shop.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
private final UserService userService;
    @GetMapping("/landing")
    public String getAdmin(Model model, Principal principal) {
        model.addAttribute("users", userService.findByEmail(principal.getName()));

        return "/Admin/dashboard";
    }

//    private final ProductService productService;
//
//    private final UserService userService;
//
////    @GetMapping("/addProduct")
////    public String getaddProduct(Model model){
////        List<Category> categories = categoryService.fetchAll();
////        model.addAttribute("categories",categories);
////        return "Admin/add_product";
////    }
//
//    @GetMapping("/addProduct")
//    public String getaddProduct(Model model){
////        List<Category> categories = categoryService.fetchAll();
////        Product product = productService.
//        model.addAttribute("product",new ProductPojo());
//        return "Admin/add_product";
//    }
//
//    @PostMapping("/saveProduct")
//    public String saveProduct(@Valid ProductPojo productPojo, BindingResult bindingResult, RedirectAttributes redirectAttributes)
//            throws Exception {
//        Map<String, String> requestError = validateRequest(bindingResult);
//        if (requestError != null) {
//            redirectAttributes.addFlashAttribute("requestError", requestError);
//            return "redirect:admin/addProduct";
//        }
//        productService.saveProduct(productPojo);
//        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
//        return "redirect:/admin/productDetails";
//    }
//
//    private Map<String, String> validateRequest(BindingResult bindingResult) {
//        if (!bindingResult.hasErrors()) {
//            return null;
//        }
//        Map<String, String> errors = new HashMap<>();
//        bindingResult.getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
//            errors.put(fieldName, message);
//        });
//        return errors;
//    }
//
//    @GetMapping("/userDetails")
//    public String getUserDetails(Model model) {
//        List<User> users = userService.fetchAll();
//
//        model.addAttribute("userList" ,users.stream().map(user ->
//                User.builder()
//                        .id(user.getId())
//                        .fullName(user.getFullName())
//                        .email(user.getEmail())
//                        .phone(user.getPhone())
//                        .build()
//        ));
//        return "Admin/user-list";
//    }
//
//    @GetMapping("/productDetails")
//    public String getProductDetials(Model model) {
//        List<Product> products = productService.fetchAll();
//
//        model.addAttribute("productList" ,products.stream().map(product ->
//                        Product.builder()
//                                .id(product.getId())
//                                .product_name(product.getProduct_name())
//                                .product_price(product.getProduct_price())
//                                .product_quantity(product.getProduct_quantity())
////                        .product_image(product.getProduct_image())
//                                .product_imageBase64(getProductImageBase64(product.getProduct_image()))
//                                .date(product.getDate())
//                                .build()
//        ));
//        return "Admin/product_list";
//    }
//
//    @GetMapping("/deleteImg/{id}")
//    public String delImg(@PathVariable("id") Integer id) {
//        productService.deleteById(id);
//        return "redirect:/admin/productDetails";
//    }
//
//    public String getProductImageBase64(String fileName) {
//        String filePath = System.getProperty("user.dir") + "/Images/product/";
//        File file = new File(filePath + fileName);
//        byte[] bytes = new byte[0];
//        try {
//            bytes = Files.readAllBytes(file.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        String base64 = Base64.getEncoder().encodeToString(bytes);
//        return base64;
//    }

}

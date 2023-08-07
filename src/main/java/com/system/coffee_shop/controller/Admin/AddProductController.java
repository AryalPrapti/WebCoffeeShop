package com.system.coffee_shop.controller.Admin;

import com.system.coffee_shop.entity.Category;
import com.system.coffee_shop.entity.Product;
import com.system.coffee_shop.pojo.ProductPojo;
import com.system.coffee_shop.services.ProductService;
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
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AddProductController {
    private final ProductService productService;

    @GetMapping("/addProduct")
    public String getaddProduct(Model model){
//        List<Category> categories = categoryService.fetchAll();
        model.addAttribute("product",new ProductPojo());
        return "Admin/add_product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid ProductPojo productPojo, BindingResult bindingResult, RedirectAttributes redirectAttributes)
            throws Exception {
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/addProduct";
        }
        productService.saveProduct(productPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/admin/productDetails";
    }

    private Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }


}

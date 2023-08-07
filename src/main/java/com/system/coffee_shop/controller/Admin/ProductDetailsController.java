package com.system.coffee_shop.controller.Admin;

import com.system.coffee_shop.entity.Product;
import com.system.coffee_shop.entity.User;
import com.system.coffee_shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProductDetailsController {
    private final ProductService productService;

    @GetMapping("/productDetails")
    public String getProductDetials(Model model) {
        List<Product> products = productService.fetchAll();

        model.addAttribute("productList" ,products.stream().map(product ->
                        Product.builder()
                                .id(product.getId())
                                .product_name(product.getProduct_name())
                                .product_price(product.getProduct_price())
                                .product_quantity(product.getProduct_quantity())
//                        .product_image(product.getProduct_image())
                                .product_imageBase64(getProductImageBase64(product.getProduct_image()))
                                .date(product.getDate())
                                .build()
        ));
        return "Admin/product_list";
    }

    @GetMapping("/deleteImg/{id}")
    public String delImg(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return "redirect:/admin/productDetails";
    }

    public String getProductImageBase64(String fileName) {

        String filePath = System.getProperty("user.dir") + "/product/"; // path to the images directory
        Path imagePath = Paths.get(filePath + fileName);

        try {
            Path symbolicLink = Paths.get("C:/product/" + fileName); // create a symbolic link to a shorter path
            if (!symbolicLink.toFile().exists()) {
                Files.createSymbolicLink(symbolicLink, imagePath);
            }
            byte[] bytes = Files.readAllBytes(symbolicLink);
            String base64 = Base64.getEncoder().encodeToString(bytes);
            return base64;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
